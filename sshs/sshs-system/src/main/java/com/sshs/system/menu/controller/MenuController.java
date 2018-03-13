package com.sshs.system.menu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.system.menu.model.Menu;
import com.sshs.system.menu.service.IMenuService;

import reactor.core.publisher.Mono;

/**
 * 系统管理->系统管理-菜单表controller类
 * 
 * @author Suny
 * @date 2018/03/13
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {
	Log logger = LogFactory.getLog(MenuController.class);
	@Resource(name = "menuService")
	private final IMenuService menuService;

	@Autowired
	public MenuController(final IMenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * 系统管理->系统管理-菜单表新增
	 * 
	 * @param menu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/menu.do")
	public Mono<Message> save(@RequestBody Menu menu, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (menuService.save(menu) > 0) {
				return Mono.justOrEmpty(new Message("100000"));
			} else {
				return Mono.justOrEmpty(new Message("100001"));
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
	@PatchMapping("/menu.do")
	public Mono<Message> update(@RequestBody Menu menu, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (menuService.update(menu) > 0) {
				return Mono.justOrEmpty(new Message("200000"));
			} else {
				return Mono.justOrEmpty(new Message("200001"));
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
	@DeleteMapping("/menu.do")
	public Mono<Message> delete(@RequestParam("menuCode") String menuCode, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (menuService.delete(menuCode) > 0) {
				return Mono.justOrEmpty(new Message("300000"));
			} else {
				return Mono.justOrEmpty(new Message("300001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-300001");
		}
	}

	/**
	 * 获取系统管理->系统管理-菜单表根据主键查询单笔记录
	 * 
	 * @param menuCode
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/menu.do")
	public Mono<Menu> getMenuById(@RequestParam("menuCode") String menuCode, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			return Mono.justOrEmpty(menuService.getById(menuCode));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-400001");
		}
	}

	/**
	 * 获取系统管理->系统管理-菜单表列表(分页查询)
	 * 
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/pageList.do")
	public Mono<Page<Menu>> getMenuPageList(@RequestBody Page<Menu> page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		menuService.findForPageList("com.sshs.system.menu.MenuDao.findForPageList", page);
		return Mono.justOrEmpty(page);
	}

	/**
	 * 获取系统管理->系统管理-菜单表列表
	 * 
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/menuList.do")
	public Mono<List<Menu>> getMenuList(@RequestBody Page<Menu> page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//page.setLimit(999999999);
		return Mono.justOrEmpty(menuService.findForList("com.sshs.system.menu.MenuDao.findForPageList", page));
	}
}
