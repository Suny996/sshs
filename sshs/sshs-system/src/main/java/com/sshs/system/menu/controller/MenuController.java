package com.sshs.system.menu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.system.menu.model.Menu;
import com.sshs.system.menu.service.IMenuService;


 /** 
 * 系统管理->系统管理-菜单表controller类
 * @author Suny
 * @date 2017/12/24
 */
@Controller
@RequestMapping(value="/system/menu")
public class MenuController extends BaseController {
	//Logger logger = Logger.getLogger(MenuController.class);
	@Resource(name="menuService")
	private IMenuService menuService;
	
	/**
	 * 系统管理->系统管理-菜单表新增
	 * 
	 * @param menu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public Message save(@RequestBody Menu menu, HttpServletRequest request, HttpServletResponse response) {
		try {
			if(menuService.save(menu)>0){
				return new Message("100000");
			} else {
			    return new Message("100001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-100001");
		}
	}

	/**
	 * 系统管理->系统管理-菜单表修改
	 * 
	 * @param menu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Message update(@RequestBody Menu menu, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (menuService.update(menu)>0) {
				return new Message("200000");
			} else {
				return new Message("200001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-200001");
		}
	}
	
		/**
	 * 系统管理->系统管理-菜单表修改
	 * 
	 * @param menu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Message delete(@RequestParam("menuId") String menuId, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (menuService.delete(menuId)>0) {
				return new Message("300000");
			} else{
				return new Message("300001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-300001");
		}
	}
	
	/**
	 * 获取系统管理->系统管理-菜单表根据主键查询单笔记录
	 * 
	 * @param menuId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getMenu.do")
	@ResponseBody
	public Menu getMenuById(@RequestParam("menuId") String menuId, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			return menuService.getById(menuId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-400001");
		}
	}
	
	/**
	 * 获取系统管理->系统管理-菜单表列表(分页查询)
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPageList.do")
	@ResponseBody
	public Page<Menu> getMenuList(@RequestBody Page<Menu> page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		menuService.findForPageList("com.sshs.system.menu.MenuDao.findForPageList",page);
		return page;
	}
}
