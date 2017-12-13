package ${coder.packageName}.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshs.core.base.controller.BaseController;
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
	 * 获取${coder.title}列表(分页查询)
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPageList.do")
	@ResponseBody
	public Page<${coder.className}> get${coder.className}List(Page<${coder.className}> page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		${coder.classDeclare}Service.findForPageList("${coder.packageName}.${coder.className}Dao.findForPageList",page);
		return page;
	}
}
