package com.reshui.account.entity;

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
@TableName("account_flow")
public class AccountFlow implements Serializable {


    /**
     * 账户流水唯一标识
     */
    @TableId("flow_id")
    private String flowId;

    /**
     * 账户唯一标识
     */
    @TableField("account_id")
    private String accountId;

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
     * 费用
     */
    @TableField("fee")
    private BigDecimal fee;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 操作类别
     */
    @TableField("operation_type")
    private String operationType;

    /**
     * 流水状态
     */
    @TableField("status")
    private String status;


}
