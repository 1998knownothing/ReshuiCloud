package com.reshui.order.service.impl;

import com.reshui.order.entity.Order;
import com.reshui.order.mapper.OrderMapper;
import com.reshui.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
