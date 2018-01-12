package com.sshs.core.handler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理类
 * 
 * @author Suny
 * 
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	private static final Log logger = LogFactory.getLog(ExceptionHandler.class);

	/**
	 * 
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception ex) {
		ModelAndView view = new ModelAndView();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ex.printStackTrace(new PrintStream(byteArrayOutputStream));

		logger.error(ex.getLocalizedMessage());
		view.setViewName("/error/500");
		return view;
	}

}
