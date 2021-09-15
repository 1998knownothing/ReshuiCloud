package com.reshui.goods.mapper;

import com.reshui.goods.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 减少库存
     *
     * @param goodsId 商品id
     * @param count 数量
     * @return
     */
    int reduceStock(@Param("goodsId") String goodsId, @Param("count") Long count);

    /**
     * 添加库存
     * @param goodsId 商品id
     * @param count 数量
     */
    void addStock(@Param("goodsId") String goodsId, @Param("count") Long count);
}
