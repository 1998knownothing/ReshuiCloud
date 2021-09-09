package com.reshui.account.api.factory;


import com.reshui.account.api.RemoteAccountService;
import com.reshui.common.core.domain.R;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 账户服务降级处理
 * 
 * @author ruoyi
 */
@Component
@Slf4j
public class RemoteAccountFallbackFactory implements FallbackFactory<RemoteAccountService>
{


    @Override
    public RemoteAccountService create(Throwable throwable)
    {
        log.error("账户服务调用失败:{}", throwable.getMessage());
        return new RemoteAccountService()
        {
            @Override
            public R<?> getAccountInfo(String userId) {
                return R.fail("获取账户信息失败:" + throwable.getMessage());
            }

        };
    }
}
