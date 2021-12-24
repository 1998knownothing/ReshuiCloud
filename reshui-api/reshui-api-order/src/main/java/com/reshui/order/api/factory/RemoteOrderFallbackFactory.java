package com.reshui.order.api.factory;



import com.reshui.common.core.domain.R;

import com.reshui.order.api.RemoteOrderService;
import com.reshui.order.api.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 订单服务降级处理
 * 
 * @author reshui
 */
@Component
@Slf4j
public class RemoteOrderFallbackFactory implements FallbackFactory<RemoteOrderService>
{


    @Override
    public RemoteOrderService create(Throwable throwable)
    {
        log.error("订单服务调用失败:{}", throwable.getMessage());
        return new RemoteOrderService()
        {
            @Override
            public R<Order> getById(String id) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }

            @Override
            public R<?> update(Order order) {
                return R.fail("更新失败:" + throwable.getMessage());
            }


        };
    }
}
