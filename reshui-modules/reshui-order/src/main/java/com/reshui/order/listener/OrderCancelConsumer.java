package com.reshui.order.listener;

import com.reshui.common.mq.config.RocketMqConstant;
import com.reshui.order.service.IOrderService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author reshui
 */
@Component
@RocketMQMessageListener(topic = RocketMqConstant.ORDER_CANCEL_TOPIC,consumerGroup = RocketMqConstant.ORDER_CANCEL_TOPIC)
public class OrderCancelConsumer implements RocketMQListener<String> {

    @Autowired
    private IOrderService orderService;

    /**
     * 订单取消状态修改后再进行其他服务
     */
    @Override
    public void onMessage(String orderId) {
        // 如果订单未支付的话，将订单设为取消状态
        orderService.cancelOrder(orderId);
    }
}