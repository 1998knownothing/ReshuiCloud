package com.reshui.account.api;

import com.reshui.account.api.factory.RemoteAccountFallbackFactory;

import com.reshui.common.core.constant.ServiceNameConstants;

import com.reshui.common.core.domain.R;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 账户服务
 * 
 * @author ruoyi
 */
@FeignClient(contextId = "remoteAccountService", value = ServiceNameConstants.ACCOUNT_SERVICE, fallbackFactory = RemoteAccountFallbackFactory.class)
public interface RemoteAccountService
{
    /**
     * 通过用户ID查询账户信息
     *
     * @param userId 用户Id
     * @return 结果
     */
    @GetMapping("/account/info/{userId}")
    public R<?> getAccountInfo(@PathVariable("userId") String userId);


}
