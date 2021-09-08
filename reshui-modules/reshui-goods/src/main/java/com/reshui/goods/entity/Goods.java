package com.reshui.goods.entity;

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
@TableName("goods")
public class Goods implements Serializable {


    /**
     * 商品唯一标识
     */
    @TableId("goods_id")
    private String goodsId;

    /**
     * 商品名
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 商品库存
     */
    @TableField("num")
    private Long num;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


}
