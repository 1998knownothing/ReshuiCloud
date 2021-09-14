package com.reshui.account.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.reshui.account.entity.Account;
import com.reshui.account.service.IAccountService;
import com.reshui.common.core.domain.R;
import com.reshui.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private IAccountService iAccountService;

    @GetMapping("/list")
    public R<?> getList(){

        return R.ok(iAccountService.list());

    }

    /**
     * 根据用户id获取账户信息
     * @param userId
     * @return
     */
    @GetMapping("/info/{userId}")
    public R<?> getAccountInfoByUserId(@PathVariable String userId){

        LambdaQueryWrapper<Account> lqw = new QueryWrapper<Account>()
                .lambda()
                .eq(Account::getUserId, userId);

        return R.ok(iAccountService.getOne(lqw));

    }

}

