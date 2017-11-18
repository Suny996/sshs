package com.sshs.system.dictionary.service;

import java.util.List;
import java.util.Map;

import com.sshs.core.page.Page;
import com.sshs.system.dictionary.model.Dictionary;

public interface IDictionaryService{
	int save(Dictionary user) throws Exception;

	int update(Dictionary user) throws Exception;

	int delete(String id) throws Exception;

	Dictionary findById(String id) throws Exception;

	Dictionary getDictionaryByCode(String dictCode);

	Map<String, Object> getList(String dictCode);

	List<Dictionary> findByParentId(String parentId);

	List<Dictionary> findAll();

	public void findForList(Page<Dictionary> page) throws Exception;

}
