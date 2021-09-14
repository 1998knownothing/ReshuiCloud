package com.reshui.order.api;


import com.reshui.common.core.constant.ServiceNameConstants;
import com.reshui.common.core.domain.R;

import com.reshui.order.api.domain.Order;
import com.reshui.order.api.factory.RemoteOrderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 订单服务
 * 
 * @author reshui
 */
@FeignClient(contextId = "remoteGoodsService", value = ServiceNameConstants.GOODS_SERVICE, fallbackFactory = RemoteOrderFallbackFactory.class)
public interface RemoteOrderService
{
    /**
     * 获取指定id订单详细信息-订单状态
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public R<?> getById(@PathVariable String id);

    /**
     * 更新订单信息
     * @param order
     * @return
     */
    @PostMapping("/update")
    public R<?> update(@PathVariable Order order);

}
