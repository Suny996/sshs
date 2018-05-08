package com.sshs.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误路由控制controller
 * 
 * @author Suny
 * @date 2018-003-12
 */
@Controller
public class RootController {
	@Value("${login.mainPage:}")
	public String mainPage;

	@RequestMapping("/")
	public String root(HttpServletRequest request, HttpServletResponse response) {
		return mainPage;
	}
}