package com.reshui.system.service.impl;

import com.reshui.system.entity.User;
import com.reshui.system.mapper.UserMapper;
import com.reshui.system.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
