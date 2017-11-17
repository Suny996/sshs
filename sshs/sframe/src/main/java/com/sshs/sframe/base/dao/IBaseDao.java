package com.sshs.sframe.base.dao;

import com.sshs.sframe.page.Page;

/**
 * @author Suny 修改时间：2017、9、11
 */
public interface IBaseDao<T>{

	/**
	 * 查找对象
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public void findForList(String sqlId, Page<T> page) throws Exception;
}
