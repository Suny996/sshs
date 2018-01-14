package com.sshs.system.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sshs.core.base.controller.BaseController;

/**
 * 类名称： 登录控制
 * 
 * @author Suny
 * @date 2018年1月8日
 * 
 * @version
 */
@Controller
@RequestMapping(value = "/system/portal")
public class LoginController extends BaseController {
	Log logger = LogFactory.getLog(LoginController.class);

	/**
	 * 登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "userPassword", required = true) String password, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("index.w");
		//Subject subject = SecurityUtils.getSubject();
		//UserPasswordToken token = new UserPasswordToken(userName, password);
		/*try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw new BusinessException("-000001");
		}*/
		return mv;
	}
}