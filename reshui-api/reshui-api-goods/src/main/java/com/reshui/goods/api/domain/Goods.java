package com.reshui.goods.api.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Goods implements Serializable {


    /**
     * 商品唯一标识
     */

    private String goodsId;

    /**
     * 商品名
     */

    private String goodsName;

    /**
     * 商品库存
     */

    private Long num;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
