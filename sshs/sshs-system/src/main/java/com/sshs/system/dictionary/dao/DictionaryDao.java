package com.sshs.system.dictionary.dao;

import java.util.List;

import com.sshs.system.dictionary.model.Dictionary;

import tk.mybatis.mapper.common.Mapper;

public interface DictionaryDao extends Mapper<Dictionary> {

	int save(Dictionary dictionary) throws Exception;

	int update(Dictionary dictionary) throws Exception;

	int delete(String dictId) throws Exception;

	Dictionary findById(String dictId) throws Exception;

	List<Dictionary> findByDictCode(String dictCode);

	List<Dictionary> findByParentId(String parentId);

	List<Dictionary> findAllDictCodes();
}
