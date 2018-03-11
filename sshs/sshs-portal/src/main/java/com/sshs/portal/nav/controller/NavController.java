package com.sshs.portal.nav.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.portal.nav.model.Nav;
import com.sshs.portal.nav.service.INavService;

import reactor.core.publisher.Mono;

/**
 * 系统管理->系统管理-菜单表controller类
 * 
 * @author Suny
 * @date 2017/12/24
 */
@RestController
@RequestMapping("/portal/nav")
public class NavController extends BaseController {
	Log logger = LogFactory.getLog(NavController.class);
	private final INavService navService;

	@Autowired
	public NavController(final INavService navService) {
		this.navService = navService;
	}

	/**
	 * portal管理->导航管理-获取顶层导航数据
	 * 
	 * @param menuId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getNavTop.do")
	@ResponseBody
	public Mono<List<Nav>> getNavCurrentUserTop(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Nav> navs = new ArrayList<Nav>();
			Nav nav1 = new Nav();
			nav1.setTitle("系统管理");
			nav1.setIcon("fa fa-apple icon-paddng2");
			nav1.setHref("javascript:$('#navtree').layuiLoad({code:'001'});");
			navs.add(nav1);
			Nav nav2 = new Nav();
			nav2.setTitle("指标管理");
			nav2.setIcon("fa fa-android");
			nav2.setHref("javascript:$('#navtree').layuiLoad({code:'002'});");
			navs.add(nav2);
			return Mono.justOrEmpty(navs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-400001");
		}
	}
	
	/**
	 * portal管理->导航管理-获取左侧属性导航数据
	 * 
	 * @param menuId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getNavTree.do")
	@ResponseBody
	public Mono<List<Nav>> getNavCurrentUserTree(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Nav> navs = new ArrayList<Nav>();
			Nav nav1 = new Nav();
			nav1.setTitle("系统管理");
			nav1.setIcon("fa fa-cubes");
			nav1.setHref("");
			navs.add(nav1);
			Nav nav2 = new Nav();
			nav2.setTitle("指标管理");
			nav2.setIcon("fa-cogs");
			nav2.setHref("#");
			nav2.setSpread(true);
			navs.add(nav2);
			
			Nav subNav1 = new Nav();
			subNav1.setTitle("机构管理");
			subNav1.setIcon("");
			subNav1.setHref("javascript:alert(123);");
			nav2.addChild(subNav1);
			return Mono.justOrEmpty(navs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-400001");
		}
	}
}
