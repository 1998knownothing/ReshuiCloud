package com.reshui.order.service;

import com.reshui.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
public interface IOrderService extends IService<Order> {

    /**
     * 提交订单
     * @param userId
     * @param order
     * @return
     */
    boolean submit(String userId, Order order);

    /**
     * 取消订单
     * @param orderId 订单id
     */
    void cancelOrder(String orderId);
}
