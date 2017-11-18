package ${coder.packageName}.service.impl;

 <#-- import javax.annotation.Resource;-->

import org.springframework.stereotype.Service;

import com.afcac.aframe.base.service.impl.BaseServiceImpl;
 <#-- import com.afcac.aframe.page.Page;

import ${coder.packageName}.dao.${coder.className}Dao;-->
import ${coder.packageName}.model.${coder.className};
import ${coder.packageName}.service.I${coder.className}Service;

 /** 
 * @see ${coder.title}service实现类
 * @author Suny
 * @date ${coder.crtDate?string("yyyy/MM/dd")}
 */
@Service("${coder.classDeclare}Service")
public class ${coder.className}ServiceImpl extends BaseServiceImpl<${coder.className}> implements I${coder.className}Service{
	<#--  
	@Resource(name = "${coder.classDeclare}Dao")
	private ${coder.className}Dao ${coder.classDeclare}Dao;
	
	/**
	 * 新增保存
	 * 
	 * @param ${coder.classDeclare}
	 * @throws Exception
	 */
	public int save(${coder.className} ${coder.classDeclare}) throws Exception {
		return ${coder.classDeclare}Dao.insert(${coder.classDeclare});
	}
	
	/**
	 * 修改保存
	 * 
	 * @param ${coder.classDeclare}
	 * @throws Exception
	 */
	public int update(${coder.className} ${coder.classDeclare}) throws Exception {
		return ${coder.classDeclare}Dao.updateByPrimaryKey(${coder.classDeclare});
	}
	
	/**
	 * 删除
	 * 
	 * @param ${coder.classDeclare}Id
	 * @throws Exception
	 */
	public int delete(String ${coder.classDeclare}Id) throws Exception {
		return ${coder.classDeclare}Dao.delete(${coder.classDeclare}Id);
	}
	
	/**
	 * 分页查询列表(主表)
	 * 
	 * @param page
	 * @throws Exception
	 */
	public void find${coder.className}ForList(Page<${coder.className}> page) throws Exception {
		${coder.classDeclare}Dao.findForPageList("${coder.packageName}.dao.${coder.className}Dao.find${coder.className}ForPageList", page);
	}
	 -->
}

