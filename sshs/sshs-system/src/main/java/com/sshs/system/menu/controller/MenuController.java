package com.sshs.system.menu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.page.Page;
import com.sshs.system.menu.model.Menu;
import com.sshs.system.menu.service.IMenuService;


 /** 
 * 系统管理->系统管理-菜单表controller类
 * @author Suny
 * @date 2017/12/13
 */
@Controller
@RequestMapping(value="/system/menu")
public class MenuController extends BaseController {
	Logger logger = Logger.getLogger(MenuController.class);
	@Resource(name="menuService")
	private IMenuService menuService;
	 
	/**
	 * 获取系统管理->系统管理-菜单表列表(分页查询)
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPageList.do")
	@ResponseBody
	public Page<Menu> getMenuList(Page<Menu> page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		menuService.findForPageList("com.sshs.system.menu.MenuDao.findForPageList",page);
		return page;
	}
}
