package com.reshui.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.reshui.common.core.domain.R;
import com.reshui.common.mq.config.RocketMqConstant;
import com.reshui.goods.api.RemoteGoodsService;
import com.reshui.order.entity.Order;
import com.reshui.order.mapper.OrderMapper;
import com.reshui.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Autowired
    private RemoteGoodsService remoteGoodsService;

    @Autowired
    private RocketMQTemplate orderCancelTemplate;


    @Autowired
    private RocketMQTemplate stockMqTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submit(String userId, Order order) {

        //存储订单 saveOrder
        this.baseMapper.insert(order);

        //调用商品服务 锁定库存
        Long goodsNum = order.getGoodsNum().longValue();
        R<?> lock = remoteGoodsService.lock(order.getGoodsId(), goodsNum, order.getOrderId(), order.getUserId());

        // 锁定不成，抛异常，让回滚订单
        if (lock.getCode()!= 200) {
            throw new RuntimeException(lock.getMsg());
        }
        //发送消息，如果三十分钟后没有支付，则取消订单
        SendStatus sendStatus = orderCancelTemplate
                .syncSend(RocketMqConstant.ORDER_CANCEL_TOPIC,
                        new GenericMessage<>(order.getOrderId()),
                        RocketMqConstant.TIMEOUT,
                        RocketMqConstant.CANCEL_ORDER_DELAY_LEVEL).getSendStatus();

        if (!Objects.equals(sendStatus,SendStatus.SEND_OK)) {
            // 消息发不出去就抛异常，发的出去无所谓
            throw new RuntimeException("系统异常");
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(String orderId) {
        log.info("cancelOrder:{}",orderId);

        LambdaQueryWrapper<Order> queryWrapper = new QueryWrapper<Order>()
                .lambda().eq(Order::getOrderId, orderId).eq(Order::getStep, "N");

        Order order = this.baseMapper.selectOne(queryWrapper);


        if (order.getOrderStatus() == "取消订单") {
            return;
        }
        //订单支付状态为N，设置订单状态为 取消订单
        this.baseMapper.cancelOrder("取消订单",order.getOrderId());
        // 通知-解锁库存状态
        SendStatus stockSendStatus = stockMqTemplate
                .syncSend(RocketMqConstant.STOCK_UNLOCK_TOPIC, new GenericMessage<>(orderId)).getSendStatus();
        if (!Objects.equals(stockSendStatus,SendStatus.SEND_OK)) {
            // 消息发不出去就抛异常，发的出去无所谓
            throw new RuntimeException("系统异常");
        }
    }
}
