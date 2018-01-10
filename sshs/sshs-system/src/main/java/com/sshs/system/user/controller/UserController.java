package com.sshs.system.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.system.user.model.User;
import com.sshs.system.user.service.IUserService;


 /** 
 * 系统管理->系统管理-用户表controller类
 * @author Suny
 * @date 2018/01/09
 */
@Controller
@RequestMapping(value="/system/user")
public class UserController extends BaseController {
	Logger logger = Logger.getLogger(UserController.class);
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	 * 系统管理->系统管理-用户表新增
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public Message save(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		try {
			if(userService.save(user)>0){
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
	 * 系统管理->系统管理-用户表修改
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Message update(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (userService.update(user)>0) {
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
	 * 系统管理->系统管理-用户表修改
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Message delete(@RequestParam("userId") String userId, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (userService.delete(userId)>0) {
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
	 * 获取系统管理->系统管理-用户表根据主键查询单笔记录
	 * 
	 * @param userId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUser.do")
	@ResponseBody
	public User getUserById(@RequestParam("userId") String userId, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			return userService.getById(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-400001");
		}
	}
	
	/**
	 * 获取系统管理->系统管理-用户表列表(分页查询)
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPageList.do")
	@ResponseBody
	public Page<User> getUserList(@RequestBody Page<User> page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		userService.findForPageList("com.sshs.system.user.UserDao.findForPageList",page);
		return page;
	}
}
