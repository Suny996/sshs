package com.sshs.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sshs.security.model.Privilege;
import com.sshs.security.model.SecurityUser;

/**
 * @author Suny
 * @date 2018-01-02
 */
@Component
public class SecurityUserService implements UserDetailsService {
	// 根据用户名查询数据库是否存在这个用户
	// @Autowired
	// private UserService userService;
	@Autowired
	private PrivilegeService privilegeService;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// SysUser sysUser = userService.findByUserName(s);
		SecurityUser userDetails = null;
		userDetails = new SecurityUser(userId, "{IRT2KWbqiuMRirzB9A46JA4LI497jeOfyXTRxzJABxI=}451bb1b0b44f634e8808d91791dea841", true, true, true, true, findUserAuthorities(userId));
		return userDetails;
	}

	public Collection<GrantedAuthority> findUserAuthorities(String userId) {
		// 定义一个接收GrantedAuthority类型的List
		List<GrantedAuthority> autthorities = new ArrayList<GrantedAuthority>();
		// 查询一个user的所有的权限
		List<Privilege> privilegeList = null;
		try {
			//privilegeList = privilegeService.findPrivilegeByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (privilegeList == null || privilegeList.size() == 0) {
			return autthorities;
		} else {
			for (Privilege p : privilegeList) {
				autthorities.add(new SimpleGrantedAuthority(p.getId()));
			}
			return autthorities;
		}
	}
}
