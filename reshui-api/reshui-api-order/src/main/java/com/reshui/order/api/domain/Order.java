package com.reshui.order.api.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class Order implements Serializable {


    /**
     * 订单唯一标识
     */
    private String orderId;

    /**
     * 用户唯一标识
     */

    private String userId;

    /**
     * 订单状态(订单创建>订单支付>订单生产>订单确认>订单完成)
     */

    private String orderStatus;

    /**
     * 商品唯一标识
     */

    private String goodsId;

    /**
     * 商品数量
     */

    private Integer goodsNum;

    /**
     * 总计费用
     */

    private BigDecimal countFee;

    /**
     * 步骤
     */

    private String step;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    /**
     * 更新时间
     */

    private LocalDateTime updateTime;


}
