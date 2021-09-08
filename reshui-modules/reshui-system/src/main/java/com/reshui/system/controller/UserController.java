package com.reshui.system.controller;


import com.reshui.common.core.web.domain.AjaxResult;
import com.reshui.system.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/list")
    public AjaxResult getList(){

        return AjaxResult.success(userService.list());

    }

}

