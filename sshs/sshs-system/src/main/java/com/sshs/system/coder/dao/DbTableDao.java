package com.sshs.system.coder.dao;

import com.sshs.core.page.Page;
import com.sshs.system.coder.model.DbTable;

import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * @author Suny
 *
 */
public interface DbTableDao extends Mapper<DbTable> {
	/**
	 * 根据ID查询对象
	 * @param tableName
	 * @return
	 */
	DbTable findById(String tableName);

	/**
	 * 查询table表列表
	 * @param page
	 */
	void findForPageList(Page<DbTable> page);
}
