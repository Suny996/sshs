package com.sshs.core.base.service;

import com.sshs.core.page.Page;

/**
 * 
 * @author Suny
 *
 * @param <T>
 */
public interface IBaseService<T> {
	/**
	 * 新增
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	int save(T model) throws Exception;

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	int update(T model) throws Exception;

	/**
	 * 删除
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	int delete(T model) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param sqlId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	Page<T> findForPageList(String sqlId, Page<T> page) throws Exception;
}
