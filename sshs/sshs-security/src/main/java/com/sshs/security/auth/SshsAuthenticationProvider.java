package com.sshs.security.auth;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sshs.core.message.Message;
import com.sshs.security.model.SecurityUser;
import com.sshs.security.password.PasswordEncoderFactory;
import com.sshs.security.service.SecurityUserServiceImpl;

/**
 * 自定义验证类
 * 
 * @author Suny
 * @date 2018-01-19
 */
@Component
public class SshsAuthenticationProvider implements AuthenticationProvider {
	private final static Log log = LogFactory.getLog(SshsAuthenticationProvider.class);
	@Resource
	private SecurityUserServiceImpl securityUserService;

	@Value("${login.password.encoder:}")
	private String encoder;

	/**
	 * 自定义验证方式
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		SecurityUser user = (SecurityUser) securityUserService.loadUserByUsername(username);
		if (user == null) {
			throw new BadCredentialsException(Message.getMessage("-010001", "用户不存在"));
		}

		// 加密过程在这里体现
		PasswordEncoder passwordEncoder = PasswordEncoderFactory.createDelegatingPasswordEncoder(encoder);
		// String encodedPassword = passwordEncoder.encode(password);
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException(Message.getMessage("-010002", "密码错误"));
		}

		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = PasswordEncoderFactory.createDelegatingPasswordEncoder();
		String encode = passwordEncoder.encode("password");
		log.info("bcrypt加密后的密码:" + encode);

		log.info("bcrypt密码对比:" + passwordEncoder.matches("password", encode));
		passwordEncoder = PasswordEncoderFactory.createDelegatingPasswordEncoder("MD5");
		encode = passwordEncoder.encode("password");
		log.info("MD5加密后的密码:" + encode);
		log.info("MD5密码对比:" + passwordEncoder.matches("password", encode));
	}

}