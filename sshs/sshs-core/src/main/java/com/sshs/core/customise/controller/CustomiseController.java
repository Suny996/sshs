package com.sshs.core.customise.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.customise.model.Customise;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.util.UuidUtil;

/**
 * 类名称： 自定义查询
 * 
 * @author Suny
 * @date 2017年10月23日
 * 
 * @version
 */
@Controller
@RequestMapping(value = "/core/custmise")
public class CustomiseController extends BaseController {
	Log logger = LogFactory.getLog(CustomiseController.class);
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 自定义查询条件-添加
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/save.do")
	@ResponseBody
	public Message saveCustomise(@RequestBody Customise customise, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			customise.setCustomiseId(UuidUtil.get32UUID());
			customise.setUserCode("admin");
			customise.setCrtDate(new Date());
			sqlSessionTemplate.insert("com.sshs.core.customise.save", customise);
			return new Message("100000");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-100001");
		}
	}

	/**
	 * 自定义查询条件-删除
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete.do")
	@ResponseBody
	public Message deleteCustomise(@RequestBody Customise customise, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			sqlSessionTemplate.delete("com.sshs.core.customise.delete", customise);
			return new Message("100000");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-100001");
		}
	}
}