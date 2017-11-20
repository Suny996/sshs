package com.sshs.system.dictionary.service;

import java.util.List;

import com.sshs.core.page.Page;
import com.sshs.system.dictionary.model.Dictionary;

public interface IDictionaryService {
	int save(Dictionary dictionary) throws Exception;

	int update(Dictionary dictionary) throws Exception;

	int delete(String id) throws Exception;

	Dictionary findById(String id) throws Exception;

	Dictionary getDictionaryByCode(String dictCode);

	List<Dictionary> findByParentId(String parentId);

	public void findForList(Page<Dictionary> page) throws Exception;
}
