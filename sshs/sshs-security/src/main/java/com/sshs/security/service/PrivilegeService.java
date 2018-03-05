package com.sshs.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.security.model.Privilege;

/**
 * @author Suny
 * @date 2018-01-28
 */
@Service
public class PrivilegeService extends BaseServiceImpl<Privilege> {

	public List<Privilege> findPrivilegeByUserId(String userId) throws Exception {
		return findForList("com.sshs.security.dao.PrivilegeDao.findPrivilegeListByUserId", userId);
	}

	public List<Privilege> findPrivilegeByUrl(String url) throws Exception {
		return findForList("com.sshs.security.dao.PrivilegeDao.findPrivilegeListByUrl", url);
	}
}
