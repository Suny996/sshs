package com.sshs.system.dictionary.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.page.Page;
import com.sshs.system.dictionary.model.Dictionary;
import com.sshs.system.dictionary.service.IDictionaryService;


@Controller
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController {

	Logger logger = Logger.getLogger(DictionaryController.class);

	@Resource
	private IDictionaryService dictionaryService;

	/**
	 * 获取所有用户列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDictionarys.do")
	@ResponseBody
	public List<Dictionary> getDictionarys(String dictCode, HttpServletRequest request) {
		Dictionary dictionary = dictionaryService.getDictionaryByCode(dictCode);
		return dictionary.getChildren();
	}

	/**
	 * 获取用户列表(分页查询)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getDictionaryList.do")
	@ResponseBody
	public Page<Dictionary> getUserList(Page<Dictionary> page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		dictionaryService.findForList(page);
		return page;
	}


	/**
	 * 添加字典并重定向
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/addDictionary.do")
	public String addDictionary(Dictionary dictionary, HttpServletRequest request) {
		try {
			dictionaryService.save(dictionary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/user/getAllUser.do";
	}

	/**
	 * 编辑用户
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateDictionary.do")
	public String updateDictionary(Dictionary dictionary, HttpServletRequest request) {
		try {
			if (dictionaryService.update(dictionary) > 0) {
				dictionary = dictionaryService.findById(dictionary.getDictId());
				request.setAttribute("dictionary", dictionary);
				return "redirect:/user/getAllUser.do";
			} else {
				return "/error.jsp";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/error.jsp";
		}
	}

	/**
	 * 根据id查询单个用户
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDictionary")
	public String getDictionary(String id, HttpServletRequest request) {
		try {
			request.setAttribute("user", dictionaryService.findById(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/sample/sample1/editUser.jsp";
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delDictionary")
	public void delDictionary(String id, HttpServletRequest request, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";

		try {
			if (dictionaryService.delete(id) > 0) {
				result = "{\"result\":\"success\"}";
			}

			response.setContentType("application/json");

			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
