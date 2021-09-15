package com.reshui.goods.api;


import com.reshui.common.core.constant.ServiceNameConstants;
import com.reshui.common.core.domain.R;
import com.reshui.common.core.web.domain.AjaxResult;
import com.reshui.goods.api.domain.Goods;
import com.reshui.goods.api.domain.GoodsFlow;
import com.reshui.goods.api.factory.RemoteGoodsFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 账户服务
 * 
 * @author reshui
 */
@FeignClient(contextId = "remoteGoodsService", value = ServiceNameConstants.GOODS_SERVICE, fallbackFactory = RemoteGoodsFallbackFactory.class)
public interface RemoteGoodsService
{

    /**
     * 扣减库存
     * @param goodsId
     * @param num
     * @param orderId
     * @param userId
     * @return
     */
    @PostMapping("/goods/lock")
    public R<?> lock(@RequestParam(value="goodsId") String goodsId, @RequestParam(value="num")Long num, @RequestParam(value="orderId")String orderId, @RequestParam(value="userId")String userId);

    /**
     * 根据id获取商品详细信息
     * @param id
     * @return
     */
//    @PostMapping("/get/{id}")
//    public R<?> getById(@PathVariable String id);
    /**
     * 更新商品库存
     * @param goods
     * @return
     */

    @PostMapping("/update")
    public R<?> updateById(@RequestBody Goods goods);

    /**
     * 生成库存流水
     * @param goodsFlow
     * @return
     */

    @PostMapping("/add")
    public R<?> add(@RequestBody GoodsFlow goodsFlow);


}
