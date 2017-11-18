package com.sshs.system.dictionary.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.page.Page;
import com.sshs.system.dictionary.dao.DictionaryDao;
import com.sshs.system.dictionary.model.Dictionary;
import com.sshs.system.dictionary.service.IDictionaryService;

@Service
@Transactional
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary> implements IDictionaryService {
	/**
	 * 字典缓存
	 */
	public static Map<String, Dictionary> dictionarys = new HashMap<String, Dictionary>();
	/**
	 * 字典下拉列表缓存
	 */
	public static Map<String, Map<String, Object>> dictLists = new HashMap<String, Map<String, Object>>();
	@Resource(name = "dictionaryDao")
	private DictionaryDao dao;

	// @Resource(name = "baseDao")
	// private IBaseDao<Dictionary> baseDao;

	public int delete(String id) throws Exception {
		return dao.delete(id);
	}

	public Dictionary findById(String id) throws Exception {
		return dao.findById(id);
	}

	public int save(Dictionary dictionary) throws Exception {
		return dao.save(dictionary);
	}

	public int update(Dictionary dictionary) throws Exception {
		return dao.update(dictionary);
	}

	public void findForList(Page<Dictionary> page) throws Exception {
		// baseDao.findForList("com.afcac.system.dictionary.dao.DictionaryDao.findForPageList",
		// page);
	}

	@Override
	public List<Dictionary> findByParentId(String parentId) {
		return dao.findByParentId(parentId);
	}

	@Override
	public List<Dictionary> findAll() {
		return dao.findAll();
	}

	@Override
	public Dictionary getDictionaryByCode(String dictCode) {
		Dictionary dict = dictionarys.get(dictCode);
		if (dict != null) {
			return dict;
		} else {
			List<Dictionary> dicts = dao.findByDictCode(dictCode);
			if (dicts != null && !dicts.isEmpty()) {
				for (Dictionary d : dicts) {
					initChildren(d);
					dictionarys.put(d.getDictCode(), d);
					return d;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param dictCode
	 * @return
	 */
	public Map<String, Object> getList(String dictCode) {
		Map<String, Object> list = dictLists.get(dictCode);
		if (list == null) {
			// list = new LinkedHashMap<String, Object>();
			Dictionary dict = getDictionaryByCode(dictCode);
			list = initList(dict);
			dictLists.put(dictCode, list);
		}
		return list;
	}

	private Map<String, Object> initList(Dictionary dict) {
		Map<String, Object> list = new LinkedHashMap<String, Object>();
		for (Dictionary d : dict.getChildren()) {
			list.put(d.getDictCode(), d.getDictName());
		}
		return list;
	}

	/**
	 * 迭代初始化字典项
	 * 
	 * @param parent
	 * @return
	 */
	private void initChildren(Dictionary parent) {
		if ("0".equals(parent.getDictType())) {
			List<Dictionary> children = dao.findByParentId(parent.getDictId());
			if (children != null && !children.isEmpty()) {
				for (Dictionary d : children) {
					initChildren(d);
					parent.addChild(d);
				}
			}
		}
	}
}
