package com.sshs.security.model;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Table;

 
/** 
 * 安全管理-> 用户表bean User类
 * @author Suny
 * @date 2018/01/09
 */
@Alias("SecurityUser")
@Table(name="SEC_USER")
public class SecurityUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 继承了User类之后，多出了一个构造方法
	 * 
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public SecurityUser(String username, String password) {
		super(username, password, true, true, true, true, new ArrayList<GrantedAuthority>());
	}
}
