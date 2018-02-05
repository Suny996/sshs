package com.sshs.core.base.service;

import java.util.List;

import com.sshs.core.page.Page;

/**
 * 基础服务接口
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
	 * 根据主键批量删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	int delete(List<String> ids) throws Exception;

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delete(String id) throws Exception;

	/**
	 * 根据主键查询单笔记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T getById(String id) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param sqlId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	Page<T> findForPageList(String sqlId, Page<T> page) throws Exception;

	/**
	 * 公共列表查询方法
	 * 
	 * @param sqlId
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	List<T> findForList(String sqlId, Object parameter) throws Exception;
}
