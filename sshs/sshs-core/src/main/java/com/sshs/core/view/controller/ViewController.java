package com.sshs.core.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称： 代码生成器
 * 
 * @author Suny
 * @date 2017年10月23日
 * 
 * @version
 */
@RestController
@RequestMapping(value = "/")
public class ViewController {
	Log logger = LogFactory.getLog(ViewController.class);

	@Autowired
	public ViewController() {
		// bthis.coderService = coderService;
	}

	/**
	 * 獲取表列表
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("**/{viewName}.w")
	public ResponseEntity<Object> getPage(@PathVariable String viewName, HttpServletRequest request) throws Exception {
		try {
			return new ResponseEntity<Object>(ViewHelper.doRequest(request), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 獲取表列表
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("**/{viewName}.dw")
	public ResponseEntity<Object> getResource(@PathVariable String viewName, HttpServletRequest request)
			throws Exception {
		try {
			return new ResponseEntity<Object>(ViewHelper.doRequest(request), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}