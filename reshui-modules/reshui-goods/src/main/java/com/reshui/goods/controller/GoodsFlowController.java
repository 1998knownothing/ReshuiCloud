package com.reshui.goods.controller;


import com.reshui.common.core.domain.R;
import com.reshui.common.core.web.domain.AjaxResult;
import com.reshui.goods.entity.Goods;
import com.reshui.goods.entity.GoodsFlow;
import com.reshui.goods.service.IGoodsFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/goodsFlow")
public class GoodsFlowController {

    @Autowired
    private IGoodsFlowService iGoodsFlowService;

    /**
     * 生成库存流水
     * @param goodsFlow
     * @return
     */

    @PostMapping("/add")
    public R<?> add(@RequestBody GoodsFlow goodsFlow){

        boolean save = iGoodsFlowService.save(goodsFlow);

        return R.ok(save);
    }

}

