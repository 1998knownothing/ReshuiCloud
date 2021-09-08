package com.reshui.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("orders")
public class Order implements Serializable {


    /**
     * 订单唯一标识
     */
    @TableId("order_id")
    private String orderId;

    /**
     * 用户唯一标识
     */
    @TableField("user_id")
    private String userId;

    /**
     * 订单状态(订单创建>订单支付>订单生产>订单确认>订单完成)
     */
    @TableField("order_status")
    private String orderStatus;

    /**
     * 商品唯一标识
     */
    @TableField("goods_id")
    private String goodsId;

    /**
     * 商品数量
     */
    @TableField("goods_num")
    private Integer goodsNum;

    /**
     * 总计费用
     */
    @TableField("count_fee")
    private BigDecimal countFee;

    /**
     * 步骤
     */
    @TableField("step")
    private String step;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
