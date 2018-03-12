package com.sshs.portal.nav.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sshs.constant.Global;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.portal.nav.model.Nav;
import com.sshs.portal.nav.service.INavService;

import reactor.core.publisher.Mono;
import tk.mybatis.mapper.util.StringUtil;

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
			@SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute(Global.USER);
			if (user != null) {
				List<Nav> navs = navService.findForList("com.sshs.portal.nav.NavDao.findNavTopForList",
						user.get("username"));
				for (Nav nav : navs) {
					if (StringUtil.isEmpty(nav.getHref())) {
						nav.setHref(
								"javascript:$(\"#navtree\").layuiLoad({parentMenuCode:\"" + nav.getCode() + "\"});");
					}
				}
				return Mono.justOrEmpty(navs);
			} else {
				throw new BusinessException("-200001");
			}
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
	public Mono<Collection<Nav>> getNavCurrentUserTree(@RequestParam("parentMenuCode") String parentMenuCode,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute(Global.USER);
			if (user != null) {
				Map<String, Object> param = new HashMap<String, Object>(2);
				param.put("username", user.get("username"));
				param.put("parentMenuCode", parentMenuCode);
				Map<String, Nav> navs = new LinkedHashMap<String, Nav>();
				List<Nav> allnavs = navService.findForList("com.sshs.portal.nav.NavDao.findNavTreeForList", param);
				for (Nav nav : allnavs) {
					if (StringUtil.isNotEmpty(nav.getHref()) && !nav.getHref().toLowerCase().startsWith("javascript")) {
						nav.setHref("javascript:$(\"#pageContent\").showPage(\"" + nav.getHref() + "\");");
					}
					Nav parent = navs.get(nav.getParent());
					if (parent != null) {
						parent.addChild(nav);
					} else {
						navs.put(nav.getCode(), nav);
					}
				}
				return Mono.justOrEmpty(navs.values());
			} else {
				throw new BusinessException("-200001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-400001");
		}
	}
}
