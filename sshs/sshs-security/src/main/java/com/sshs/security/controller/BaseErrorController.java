package com.sshs.security.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "error")
public class BaseErrorController implements ErrorController {
	private static final Log logger = LogFactory.getLog(BaseErrorController.class);

	public String getErrorPath(int status) {
		logger.info("出错啦！进入自定义错误控制器");
		return "/404.html";
	}

	@Override
	public String getErrorPath() {
		logger.info("出错啦！进入自定义错误控制器");
		return getErrorPath(-1);
	}

	@RequestMapping
	public void error(HttpServletRequest request, HttpServletResponse response) {
		// return getErrorPath(response.getStatus());
		try {
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n"
					+ "<title>访问的页面不存在</title>\n" + "</head>\n" + "<body>系统错误：" + response.getStatus() + "</body>\n"
					+ "</html>");

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}