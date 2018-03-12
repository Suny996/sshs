package com.sshs.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 安全管理-> 用户表bean User类
 * 
 * @author Suny
 * @date 2018/01/09
 */
@Alias("SecurityUser")
@Table(name = "SEC_USER")
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

	/**
	 * 
	 * @return
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>(10);
		map.put("username", this.getUsername());
		map.put("password", this.getPassword());
		map.put("enabled", this.isEnabled());
		map.put("accountNonExpired", this.isAccountNonExpired());
		map.put("credentialsNonExpired", this.isCredentialsNonExpired());
		map.put("accountNonLocked", this.isAccountNonLocked());
		map.put("authorities", this.getAuthorities());
		return map;
	}
}
