package com.sshs.system.coder.dao;

import com.sshs.core.page.Page;
import com.sshs.system.coder.model.DbTable;

import tk.mybatis.mapper.common.Mapper;

public interface DbTableDao extends Mapper<DbTable> {
	DbTable findById(String tableName);

	void findForPageList(Page<DbTable> page);
}
