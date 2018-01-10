package com.sshs.system.user.service.impl;


import org.springframework.stereotype.Service;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.system.user.model.User;
import com.sshs.system.user.service.IUserService;

 /** 
 * 系统管理->系统管理-用户表service实现类
 * @author Suny
 * @date 2018/01/09
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService{
}

