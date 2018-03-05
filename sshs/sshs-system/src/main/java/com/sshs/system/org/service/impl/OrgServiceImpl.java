package com.sshs.system.org.service.impl;


import org.springframework.stereotype.Service;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.system.org.model.Org;
import com.sshs.system.org.service.IOrgService;

 /** 
 * 系统管理->系统管理-机构表service实现类
 * @author Suny
 * @date 2018/01/17
 */
@Service("orgService")
public class OrgServiceImpl extends BaseServiceImpl<Org> implements IOrgService{
}

