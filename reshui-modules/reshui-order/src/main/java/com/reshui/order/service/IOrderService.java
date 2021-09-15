package com.reshui.order.service;

import com.reshui.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
    Long submit(Long userId, Order order);

}
