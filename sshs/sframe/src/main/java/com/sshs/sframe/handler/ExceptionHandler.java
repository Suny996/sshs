package com.sshs.sframe.handler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 
 * @author Suny
 * 
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	private static final Logger logger = Logger
			.getLogger(ExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object o, Exception ex) {
		/*
		 * if (ex instanceof MaxUploadSizeExceededException) { //
		 * WebUtils.writeJson(httpServletResponse, "长传的文件大小超过" +
		 * ((MaxUploadSizeExceededException) ex).getMaxUploadSize() +
		 * "字节限制,上传失败!"); return null; }
		 */
		ModelAndView view = new ModelAndView();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ex.printStackTrace(new PrintStream(byteArrayOutputStream));
		//String exception = byteArrayOutputStream.toString();

		// view.getModel().put("error", "URL:" +
		// WebUtils.getWebUrlPath(httpServletRequest) +
		// httpServletRequest.getRequestURI() + "\r\n\r\nERROR:" + exception);
		logger.error(ex.getLocalizedMessage());
		view.setViewName("/error/500");
		return view;
	}

}
