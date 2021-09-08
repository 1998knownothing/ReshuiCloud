package com.reshui.goods.controller;


import com.reshui.common.core.web.domain.AjaxResult;
import com.reshui.goods.service.IGoodsService;
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
@RequestMapping("/goods")
public class GoodsController {


    @Resource
    private IGoodsService iGoodsService;

    @GetMapping("/list")
    public AjaxResult getList(){

        return AjaxResult.success(iGoodsService.list());

    }
}

