package com.reshui.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.reshui.common.core.domain.R;
import com.reshui.common.mq.config.RocketMqConstant;
import com.reshui.goods.entity.Goods;
import com.reshui.goods.entity.GoodsFlow;
import com.reshui.goods.mapper.GoodsFlowMapper;
import com.reshui.goods.mapper.GoodsMapper;
import com.reshui.goods.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reshui.order.api.RemoteOrderService;
import com.reshui.order.api.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {


    @Autowired
    private RocketMQTemplate stockMqTemplate;

    @Resource
    private GoodsFlowMapper goodsFlowMapper;

    @Resource
    private RemoteOrderService remoteOrderService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean lock(String goodsId, Long num,String orderId,String userId){
        // 减商品库存

        int reduceStockIsSuccess = this.baseMapper.reduceStock(goodsId, num);
        if(reduceStockIsSuccess<1){
            throw new RuntimeException("库存不足!goodsId:"+goodsId);
        }
        // (记入商品库存流水日志)保存库存锁定信息

        GoodsFlow goodsFlow = new GoodsFlow();
        goodsFlow.setUserId(userId);
        goodsFlow.setNum(num);
        goodsFlow.setGoodsId(goodsId);
        goodsFlow.setOrderId(orderId);
        goodsFlow.setType("-");
        goodsFlow.setIsDelete("N");
        goodsFlow.setCreateTime(LocalDateTime.now());

        goodsFlowMapper.insert(goodsFlow);
        // 一个小时后解锁库存
        //延时信息无法立即在可视化界面上查看
        SendStatus sendStatus =
                stockMqTemplate.syncSend(RocketMqConstant.STOCK_UNLOCK_TOPIC, new GenericMessage<>(orderId)
                , RocketMqConstant.TIMEOUT, RocketMqConstant.CANCEL_ORDER_DELAY_LEVEL + 1).getSendStatus();

        //stockMqTemplate.convertAndSend(RocketMqConstant.STOCK_UNLOCK_TOPIC, "order-string-test");

        if (!Objects.equals(sendStatus,SendStatus.SEND_OK)) {
            // 消息发不出去就抛异常，发的出去无所谓
            throw new RuntimeException("服务器出了点小差");
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unlockStock(String orderId) {
        log.info("unlockStock:{}",orderId);
        //远程调用 获取订单状态
        R<Order> orderResponse = remoteOrderService.getById(orderId);
        log.info("unlockStock.orderResponse:{}",orderResponse.toString());
        if (orderResponse.getCode()!=200) {
            throw new RuntimeException(orderResponse.getMsg());
        }
        Order order = orderResponse.getData();
        // 该订单没有下单成功，或订单已取消，赶紧解锁库存
        if(order.getOrderStatus().equals("取消订单") == false){
            return;
        }
        //根据订单号 获取锁定的库存
        LambdaQueryWrapper<GoodsFlow> queryWrapper = new QueryWrapper<GoodsFlow>()
                .lambda().eq(GoodsFlow::getOrderId, orderId);
        GoodsFlow goodsFlow = goodsFlowMapper.selectOne(queryWrapper);
        goodsFlow.setIsDelete("Y");
        //还原商品库存
        this.baseMapper.addStock(goodsFlow.getGoodsId(),goodsFlow.getNum());
        //解锁库存
        goodsFlowMapper.updateById(goodsFlow);
    }

}
