package com.reshui.account.service.impl;

import com.reshui.account.entity.Account;
import com.reshui.account.mapper.AccountMapper;
import com.reshui.account.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
