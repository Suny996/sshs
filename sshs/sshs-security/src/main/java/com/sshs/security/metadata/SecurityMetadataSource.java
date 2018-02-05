package com.sshs.security.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.sshs.security.model.Privilege;
import com.sshs.security.service.PrivilegeService;

/**
 * 获取当前url需要的权限
 * @author Suny
 * @date 2018-01-12
 */
@Component
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private PrivilegeService privilegeService;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
		List<Privilege> urlPrivileges = null;
		try {
			urlPrivileges = privilegeService.findPrivilegeByUrl(url);
			if (urlPrivileges == null || urlPrivileges.isEmpty()) {
				// 截取得到url
				int firstQuestionMarkIndex = url.indexOf("?");
				if (firstQuestionMarkIndex != -1) {
					url = url.substring(0, firstQuestionMarkIndex);
				}
			}
			urlPrivileges = privilegeService.findPrivilegeByUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将我们查询的权限加入configAttributes
		if (urlPrivileges != null && urlPrivileges.size() > 0) {
			for (Privilege p : urlPrivileges) {
				ConfigAttribute ca = new SecurityConfig(p.getId());
				configAttributes.add(ca);
			}
		} else {
			//默认是登录即可访问
			configAttributes = SecurityConfig.createList("ROLE_LOGIN");
		}
		return configAttributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return false;
	}
}
