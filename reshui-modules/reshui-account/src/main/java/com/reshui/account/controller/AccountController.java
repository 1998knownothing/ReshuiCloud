package com.reshui.account.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.reshui.account.entity.Account;
import com.reshui.account.service.IAccountService;
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
    public AjaxResult getList(){

        return AjaxResult.success(iAccountService.list());

    }

    @GetMapping("/info/{userId}")
    public AjaxResult getAccountInfoByUserId(@PathVariable String userId){

        LambdaQueryWrapper<Account> lqw = new QueryWrapper<Account>()
                .lambda()
                .eq(Account::getUserId, userId);

        return AjaxResult.success(iAccountService.getOne(lqw));

    }

}

