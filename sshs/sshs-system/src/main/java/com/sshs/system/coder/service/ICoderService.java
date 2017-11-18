package com.sshs.system.coder.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.core.page.Page;
import com.sshs.system.coder.model.Coder;
import com.sshs.system.coder.model.Column;

/**
 * 类名称：代码生成器接口类
 * @author Suny 
 * @date 2017年10月24日
 * @version
 */
public interface ICoderService extends IBaseService<Coder> {

	/**
	 * 新增
	 * 
	 * @param coder
	 * @throws Exception
	 */
	public int save(Coder coder) throws Exception;

	/**
	 * 删除
	 * 
	 * @param coderId
	 * @throws Exception
	 */
	public int delete(String coderId) throws Exception;

	/**
	 * 通过id获取数据
	 * 
	 * @param coderId
	 * @throws Exception
	 */
	public Coder findById(String coderId) throws Exception;

	/**
	 * 列表(主表)
	 * 
	 * @param page
	 * @throws Exception
	 */
	public void findColumnForList(Page<Column> page) throws Exception;
	

}
