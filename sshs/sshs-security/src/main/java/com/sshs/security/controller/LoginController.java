package com.sshs.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Suny
 * @version 1.0.0
 *
 */
@Controller
public class LoginController {

	@RequestMapping("/main.do")
	public String main() {
		return "main.html";
	}

	@RequestMapping("/error.do")
	public String error() {
		return "error.html";
	}

	@RequestMapping("/login.do")
	public String login() {
		return "/index.html";
	}

}