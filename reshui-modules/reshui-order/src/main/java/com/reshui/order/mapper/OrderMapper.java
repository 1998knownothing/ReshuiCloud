package com.reshui.order.mapper;

import com.reshui.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
public interface OrderMapper extends BaseMapper<Order> {

    int cancelOrder(@Param("orderStatus") String orderStatus,@Param("orderId") String orderId);
}
