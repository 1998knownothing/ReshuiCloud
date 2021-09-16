package com.reshui.goods.service.impl;

import com.reshui.common.mq.config.RocketMqConstant;
import com.reshui.goods.entity.Goods;
import com.reshui.goods.entity.GoodsFlow;
import com.reshui.goods.mapper.GoodsFlowMapper;
import com.reshui.goods.mapper.GoodsMapper;
import com.reshui.goods.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {


    @Autowired
    private RocketMQTemplate stockMqTemplate;

    @Resource
    private GoodsFlowMapper goodsFlowMapper;


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

}
