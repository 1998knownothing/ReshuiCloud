package com.reshui.order.service.impl;

import com.reshui.order.entity.Order;
import com.reshui.order.mapper.OrderMapper;
import com.reshui.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long submit(Long userId, Order order) {

        //存储订单 saveOrder

        //调用商品服务 锁定库存

        //锁定不成功 ，抛出异常，让订单回滚

        //发送消息，如果三十分钟后没有支付，则取消订单

        // 消息发不出去就抛异常

        return null;
    }
}
