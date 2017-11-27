package com.sshs.system.coder.controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.core.util.ReflectHelper;
import com.sshs.core.util.Serializabler;
import com.sshs.system.coder.helper.CodeGenerator;
import com.sshs.system.coder.model.Coder;
import com.sshs.system.coder.model.Column;
import com.sshs.system.coder.model.DbTable;
import com.sshs.system.coder.service.ICoderService;
import com.sshs.system.coder.service.IDbTableService;

/**
 * 类名称： 代码生成器 创建人：Suny 修改时间：2017年10月23日
 * 
 * @version
 */
@Controller
@RequestMapping(value = "/system/coder")
public class CoderController extends BaseController {
	Logger logger = Logger.getLogger(CoderController.class);
	@Resource(name = "coderService")
	private ICoderService coderService;
	@Resource(name = "dbTableService")
	private IDbTableService dbTableService;

	/**
	 * 獲取表列表
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTableList.do")
	@ResponseBody
	public Page<DbTable> getTableList(@RequestBody Page<DbTable> page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 从后台代码获取国际化信息
		RequestContext requestContext1 = new RequestContext(request);

		logger.debug(request.getSession().getId() + ">>>>>>>xxxx中文>>>"+request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME)+">>>>" + requestContext1.getMessage("male"));
		request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("en","US"));
		RequestContext requestContext = new RequestContext(request);
		logger.debug(">>>>>>>中文>>>>>>>" + requestContext.getMessage("male"));
		return dbTableService.findForPageList("com.sshs.system.coder.dao.CoderDao.findDbTableForPageList", page);
	}

	/**
	 * 獲取字段列表
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getColunmList.do")
	@ResponseBody
	public Page<Column> getColunmList(@RequestBody Page<Column> page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		coderService.findColumnForList(page);
		return page;
	}

	/**
	 * 生成代码
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/runCoder.do")
	@ResponseBody
	public Message runCoder(@RequestBody Coder coder, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			for (Column col : coder.getFields()) {
				if (StringUtils.isEmpty(coder.getTableName())) {
					coder.setTableName(col.getTableName());
					coder.setTableComment(col.getTableComment());
				}
				col.setPropertyName(ReflectHelper.getPropertyName(col.getColumnName()));
				col.setPropertyType(CodeGenerator.getPropertyType(col.getColumnType()));

				// 主键使用UUID，页面不控制非空验证
				if ("Y".equalsIgnoreCase(col.getPrimaryKeyFlag())) {
					col.setRequiredFlag("Y");
				}
			}

			coder.setColumns(Serializabler.Object2Bytes(coder.getFields()));

			CodeGenerator.generate(coder);
			coderService.save(coder);
			return new Message("0000", "成功！");
		} catch (Exception e) {
			throw new BusinessException(-10001, e.getMessage());
		}
	}
}