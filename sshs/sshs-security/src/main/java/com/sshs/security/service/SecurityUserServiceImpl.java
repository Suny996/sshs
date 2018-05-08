package com.sshs.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.message.Message;
import com.sshs.security.model.Privilege;
import com.sshs.security.model.SecurityUser;

/**
 * @author Suny
 * @date 2018-01-02
 */
@Component
public class SecurityUserServiceImpl extends BaseServiceImpl<SecurityUser> implements UserDetailsService {
	@Resource
	private PrivilegeService privilegeService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecurityUser userDetails = null;
		try {
			List<SecurityUser> list = findForList("com.sshs.security.dao.SecurityUserDao.findSecurityUserByUserName",
					username);
			if (list == null || list.isEmpty()) {
				throw new UsernameNotFoundException(Message.getMessage("-010003", "用户不存在"));
			}
			SecurityUser user = list.get(0);
			userDetails = new SecurityUser(user.getName(), user.getPassword(), true, true, true, true,
					findUserAuthorities(user.getName()));
			return userDetails;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<GrantedAuthority> findUserAuthorities(String userName) {
		// 定义一个接收GrantedAuthority类型的List
		List<GrantedAuthority> autthorities = new ArrayList<GrantedAuthority>();
		// 查询一个user的所有的权限
		List<Privilege> privilegeList = null;
		try {
			privilegeList = privilegeService.findPrivilegeByUserId(userName);
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
