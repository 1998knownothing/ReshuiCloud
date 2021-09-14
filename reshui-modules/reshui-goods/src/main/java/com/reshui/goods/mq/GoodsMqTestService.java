package com.reshui.goods.mq;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author acer
 */
@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "shop-user", topic = "order-topic")
public class GoodsMqTestService implements RocketMQListener<String> {

    @Override
    public void onMessage(String order) {
        log.info("收到一个订单信息{},接下来发送短信", JSON.toJSONString(order));
    }

}
