package com.reshui.goods.service;

import com.reshui.goods.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
public interface IGoodsService extends IService<Goods> {

    public boolean lock(String goodsId, Long num,String orderId,String userId);

    public void unlockStock(String orderId) ;
}
