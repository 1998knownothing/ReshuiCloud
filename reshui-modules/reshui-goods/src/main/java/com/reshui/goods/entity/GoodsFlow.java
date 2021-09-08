package com.reshui.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("goods_flow")
public class GoodsFlow implements Serializable {


    /**
     * 商品流水唯一流水号标识
     */
    @TableId("flow_id")
    private String flowId;

    /**
     * 商品唯一标识
     */
    @TableField("goods_id")
    private String goodsId;

    /**
     * 操作类别
     */
    @TableField("type")
    private String type;

    /**
     * 用户唯一标识
     */
    @TableField("user_id")
    private String userId;

    /**
     * 订单唯一标识
     */
    @TableField("order_id")
    private String orderId;

    /**
     * 数量
     */
    @TableField("num")
    private String num;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
