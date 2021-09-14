package com.reshui.account.controller;


import com.reshui.account.entity.AccountFlow;
import com.reshui.account.service.IAccountFlowService;
import com.reshui.account.service.IAccountService;
import com.reshui.common.core.domain.R;
import com.reshui.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/accountFlow")
public class AccountFlowController {

    @Autowired
    private IAccountFlowService accountFlowService;


    /**
     * 生成账户流水信息
     * @param accountFlow
     * @return
     */
    @PostMapping("/add")
    public R<?> add(@RequestBody AccountFlow accountFlow){

        boolean save = accountFlowService.save(accountFlow);

        return R.ok(save);
    }


}

