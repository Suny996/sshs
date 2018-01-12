package com.sshs.core.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sshs.core.exception.BusinessException;
import com.sshs.core.util.UuidUtil;

/**
 * @author Suny
 * @date 2017/9/11
 */
@Controller
public abstract class BaseController {

	protected Log logger = LogFactory.getLog(this.getClass());

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
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
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

	public static void logBefore(Log logger, String interfaceName) {
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Log logger) {
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

	/** 基于@ExceptionHandler异常处理 */
	@ExceptionHandler
	@ResponseBody
	public Map<String, Object> handleAndReturnData(HttpServletRequest request, HttpServletResponse response,
			Exception e) {

		Map<String, Object> data = new HashMap<String, Object>(10);
		if (e instanceof BusinessException) {
			BusinessException ex = (BusinessException) e;
			data.put("code", ex.getCode());
		}
		data.put("msg", e.getMessage());
		data.put("excetion", e.getClass().getSimpleName());
		return data;
	}
}
