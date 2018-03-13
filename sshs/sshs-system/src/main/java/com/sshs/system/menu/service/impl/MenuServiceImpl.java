package com.sshs.system.menu.service.impl;


import org.springframework.stereotype.Service;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.system.menu.model.Menu;
import com.sshs.system.menu.service.IMenuService;

 /** 
 * 系统管理->系统管理-菜单表service实现类
 * @author Suny
 * @date 2018/03/13
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService{
}

