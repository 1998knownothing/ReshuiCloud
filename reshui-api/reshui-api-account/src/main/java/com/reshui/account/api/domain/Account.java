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
public class Account implements Serializable {


    /**
     * 账户唯一标识
     */
    private String accountId;

    /**
     * 账户余额
     */
    private BigDecimal money;

    /**
     * 用户唯一标识
     */
    private String userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
