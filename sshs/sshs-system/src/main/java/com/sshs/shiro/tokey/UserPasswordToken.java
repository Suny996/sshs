package com.sshs.shiro.tokey;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.sshs.core.util.Configure;

public class UserPasswordToken extends UsernamePasswordToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserPasswordToken(final String username, final String password) {
		this(username, password, false);
	}

	public UserPasswordToken(final String username, final String password, final boolean rememberMe) {
		super.setUsername(username);
		super.setPassword(password.toCharArray());
		super.setRememberMe(rememberMe);
		super.setHost(null);
	}
}
