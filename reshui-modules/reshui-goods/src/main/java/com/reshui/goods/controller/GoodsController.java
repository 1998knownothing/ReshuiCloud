package com.reshui.goods.controller;


import com.reshui.common.core.domain.R;
import com.reshui.common.core.web.domain.AjaxResult;
import com.reshui.goods.entity.Goods;
import com.reshui.goods.service.IGoodsService;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 获取商品列表
     * @return
     */
    @GetMapping("/list")
    public R<?> getList(){

        return R.ok(iGoodsService.list());

    }

    /**
     * 根据id获取商品详细信息
     * @param id
     * @return
     */
    @PostMapping("/get/{id}")
    public R<?> getById(@PathVariable String id){
        Goods byId = iGoodsService.getById(id);
        return R.ok(byId);
    }
    /**
     * 更新商品库存
     * @param goods
     * @return
     */

    @PostMapping("/update")
    public R<?> updateById(@RequestBody Goods goods){
        boolean updateById = iGoodsService.updateById(goods);

        return R.ok(updateById);
    }

    /**
     * 扣减库存
     * @param goodsId
     * @param num
     * @param orderId
     * @param userId
     * @return
     */
    @PostMapping("/lock")
    public R<?> lock(String goodsId, Long num,String orderId,String userId){
        boolean lock = iGoodsService.lock(goodsId, num, orderId, userId);
        return R.ok(lock);
    }

}

