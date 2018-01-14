package com.sshs.toolkit.coder.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.core.page.Page;
import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;

/**
 * 类名称：代码生成器接口类
 * 
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
	 * @return
	 */
	public int save(Coder coder) throws Exception;

	/**
	 * 删除
	 * 
	 * @param coderId
	 * @throws Exception
	 * @return
	 */
	public int delete(String coderId) throws Exception;

	/**
	 * 通过id获取数据
	 * 
	 * @param coderId
	 * @throws Exception
	 * @return
	 */
	public Coder findById(String coderId) throws Exception;

	/**
	 * 列表(主表)
	 * 
	 * @param page
	 * @throws Exception
	 * @return
	 */
	public Page<Column> findColumnForList(Page<Column> page) throws Exception;

}
