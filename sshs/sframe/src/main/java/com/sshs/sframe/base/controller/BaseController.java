package com.sshs.sframe.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sshs.sframe.exception.BusinessException;
import com.sshs.sframe.util.UuidUtil;

/**
 * @author Suny
 * @date 2017/9/11
 */
@Controller
public abstract class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 得到ModelAndView
	 * 
	 * @return
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	/**
	 * 得到request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 得到32位的uuid
	 * 
	 * @return
	 */
	public String get32UUID() {
		return UuidUtil.get32UUID();
	}

	public static void logBefore(Logger logger, String interfaceName) {
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Logger logger) {
		logger.info("end");
		logger.info("");
	}

	protected HttpServletRequest getServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	protected ServletContext getServletContext() {
		HttpServletRequest request = getServletRequest();
		if (request != null) {
			return request.getSession().getServletContext();
		}

		return null;
	}

	/*
	 * protected HttpServletResponse getServletResponse() { return
	 * RequestResponseContext.getResponse(); }
	 * 
	 * protected void writeResponse(ActionResult result) throws IOException {
	 * HttpServletResponse response = getServletResponse();
	 * response.setContentType("text/html; charset=utf-8"); OutputStream stream
	 * = response.getOutputStream();
	 * stream.write(JsonUtil.toJson(result).getBytes("utf-8")); stream.flush();
	 * stream.close(); }
	 */

	/** 基于@ExceptionHandler异常处理 */
	/*
	 * @ExceptionHandler public ModelAndView
	 * handleAndReturnPage(HttpServletRequest request, HttpServletResponse
	 * response, Exception ex) {
	 * 
	 * ModelAndView mv = new ModelAndView("Exception") ; mv.addObject("ex", ex);
	 * 
	 * // 根据不同错误转向不同页面 if (ex instanceof BusinessException) { return mv; } else
	 * { return mv; //返回Exception.jsp页面 } }
	 */

	/** 基于@ExceptionHandler异常处理 */
	@ExceptionHandler
	@ResponseBody
	public Map<String, Object> handleAndReturnData(HttpServletRequest request, HttpServletResponse response, Exception e) {

		Map<String, Object> data = new HashMap<String, Object>();
		if (e instanceof BusinessException) {
			BusinessException ex = (BusinessException) e;
			data.put("code", ex.getCode());
		}
		data.put("msg", e.getMessage());
		data.put("excetion", e.getClass().getSimpleName());
		return data;
	}
}
