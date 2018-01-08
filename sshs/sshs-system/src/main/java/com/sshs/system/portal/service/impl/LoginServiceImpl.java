package com.sshs.system.portal.service.impl;

import org.springframework.stereotype.Service;

import com.sshs.system.portal.service.ILoginService;

/**
 * 类名称：登录服务
 * 
 * @author Suny
 * @date 2015年11月24日
 * @version 1.0
 */
@Service("loginService")
public class LoginServiceImpl implements ILoginService {
	/**
	 * 登录
	 * 
	 * @param name
	 * @param password
	 * @throws Exception
	 */
	@Override
	public int login(String name, String password) throws Exception {
		return 0;
	}
}
