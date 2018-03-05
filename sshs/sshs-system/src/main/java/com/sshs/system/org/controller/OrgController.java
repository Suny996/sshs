package com.sshs.system.org.controller;

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
import com.sshs.system.org.model.Org;
import com.sshs.system.org.service.IOrgService;

import reactor.core.publisher.Mono;

 /** 
 * 系统管理->系统管理-机构表controller类
 * @author Suny
 * @date 2018/01/17
 */
@RestController
@RequestMapping("/system/org")
public class OrgController extends BaseController {
	Log logger = LogFactory.getLog(OrgController.class);
	@Resource(name="orgService")
	private final IOrgService orgService;
	@Autowired
	public OrgController(final IOrgService orgService) {
		this.orgService = orgService;
	}
	
	/**
	 * 系统管理->系统管理-机构表新增
	 * 
	 * @param org
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/org.do")
	public Mono<Message> save(@RequestBody Org org, HttpServletRequest request, HttpServletResponse response) {
		try {
			if(orgService.save(org)>0){
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
	 * 系统管理->系统管理-机构表修改
	 * 
	 * @param org
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PatchMapping("/org.do")
	public Mono<Message> update(@RequestBody Org org, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (orgService.update(org)>0) {
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
	 * 系统管理->系统管理-机构表修改
	 * 
	 * @param org
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/org.do")
	public Mono<Message> delete(@RequestParam("orgId") String orgId, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (orgService.delete(orgId)>0) {
				return Mono.justOrEmpty(new Message("300000"));
			} else{
				return Mono.justOrEmpty(new Message("300001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-300001");
		}
	}
	
	/**
	 * 获取系统管理->系统管理-机构表根据主键查询单笔记录
	 * 
	 * @param orgId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/org.do")
	public Mono<Org> getOrgById(@RequestParam("orgId") String orgId, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			return Mono.justOrEmpty(orgService.getById(orgId));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-400001");
		}
	}
	
	/**
	 * 获取系统管理->系统管理-机构表列表(分页查询)
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/pageList.do")
	public Mono<Page<Org>> getOrgList(@RequestBody Page<Org> page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		orgService.findForPageList("com.sshs.system.org.OrgDao.findForPageList",page);
		return Mono.justOrEmpty(page);
	}
}
