package com.sshs.system.coder.dao;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.sshs.system.coder.model.Coder;
import com.sshs.system.coder.model.Column;

public interface CoderDao extends Mapper<Coder> {
	int save(Coder coder);

	int update(Coder coder);

	int delete(String dictId);

	Coder findById(String dictId);

	List<Column> findColumnAll(Map<String, Object> param);
}
