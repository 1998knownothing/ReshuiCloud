package com.reshui.account.api.domain;


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
public class AccountFlow implements Serializable {


    /**
     * 账户流水唯一标识
     */
    private String flowId;

    /**
     * 账户唯一标识
     */
    private String accountId;

    /**
     * 用户唯一标识
     */
    private String userId;

    /**
     * 订单唯一标识
     */
    private String orderId;

    /**
     * 费用
     */
    private BigDecimal fee;


    /**
     * 备注
     */
    private String remark;

    /**
     * 操作类别
     */
    private String operationType;

    /**
     * 流水状态
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标识 (Y/N)
     */
    private String isDelete;
}
