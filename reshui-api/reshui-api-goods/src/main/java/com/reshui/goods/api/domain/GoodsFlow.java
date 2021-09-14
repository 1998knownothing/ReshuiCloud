package com.reshui.goods.api.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class GoodsFlow implements Serializable {


    /**
     * 商品流水唯一流水号标识
     */
    private String flowId;

    /**
     * 商品唯一标识
     */
    private String goodsId;

    /**
     * 操作类别
     */
    private String type;

    /**
     * 用户唯一标识
     */
    private String userId;

    /**
     * 订单唯一标识
     */
    private String orderId;

    /**
     * 数量
     */
    private String num;

    /**
     * 备注
     */
    private String remark;


}
