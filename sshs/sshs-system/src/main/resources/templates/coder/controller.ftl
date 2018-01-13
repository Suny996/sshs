package ${coder.packageName}.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import ${coder.packageName}.model.${coder.className};
import ${coder.packageName}.service.I${coder.className}Service;


 /** 
 * ${coder.title}controller类
 * @author ${coder.systemUser}
 * @date ${coder.crtDate?string("yyyy/MM/dd")}
 */
@Controller
@RequestMapping(value="/${coder.modelName}/${coder.functionName}")
public class ${coder.className}Controller extends BaseController {
	Logger logger = Logger.getLogger(${coder.className}Controller.class);
	@Resource(name="${coder.classDeclare}Service")
	private I${coder.className}Service ${coder.classDeclare}Service;
	
	/**
	 * ${coder.title}新增
	 * 
	 * @param ${coder.classDeclare}
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public Message save(@RequestBody ${coder.className} ${coder.classDeclare}, HttpServletRequest request, HttpServletResponse response) {
		try {
			if(${coder.classDeclare}Service.save(${coder.classDeclare})>0){
				return new Message("100000");
			} else {
			    return new Message("100001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-100001");
		}
	}

	/**
	 * ${coder.title}修改
	 * 
	 * @param ${coder.classDeclare}
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Message update(@RequestBody ${coder.className} ${coder.classDeclare}, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (${coder.classDeclare}Service.update(${coder.classDeclare})>0) {
				return new Message("200000");
			} else {
				return new Message("200001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-200001");
		}
	}
	
		/**
	 * ${coder.title}修改
	 * 
	 * @param ${coder.classDeclare}
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Message delete(@RequestParam("${coder.idName}") String ${coder.idName}, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (${coder.classDeclare}Service.delete(${coder.idName})>0) {
				return new Message("300000");
			} else{
				return new Message("300001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-300001");
		}
	}
	
	/**
	 * 获取${coder.title}根据主键查询单笔记录
	 * 
	 * @param ${coder.idName}
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/get${coder.className}.do")
	@ResponseBody
	public ${coder.className} get${coder.className}ById(@RequestParam("${coder.idName}") String ${coder.idName}, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			return ${coder.functionName}Service.getById(${coder.idName});
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("-400001");
		}
	}
	
	/**
	 * 获取${coder.title}列表(分页查询)
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPageList.do")
	@ResponseBody
	public Page<${coder.className}> get${coder.className}List(@RequestBody Page<${coder.className}> page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		${coder.classDeclare}Service.findForPageList("${coder.packageName}.${coder.className}Dao.findForPageList",page);
		return page;
	}
}
