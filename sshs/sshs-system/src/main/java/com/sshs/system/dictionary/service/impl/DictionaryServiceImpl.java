package com.sshs.system.dictionary.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.page.Page;
import com.sshs.core.util.DictionaryUtil;
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

	@Resource(name = "dictionaryDao")
	private DictionaryDao dao;

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
	 * 初始化字典项
	 * 
	 * @return
	 *//*
		 * private List<Dictionary> findAllDictCodes() { return dao.findAllDictCodes();
		 * }
		 */

	/**
	 * 初始化字典列表,系统启动时执行缓存
	 * 
	 * @param dict
	 * @return
	 */
	@PostConstruct
	public void initDictList() {
		// DictionaryServiceImpl dictService = new DictionaryServiceImpl();
		List<Dictionary> dictCodes = dao.findAllDictCodes();
		for (Dictionary dict : dictCodes) {// 字典项
			initChildren(dict);
			List<Map<String, Object>> dictProj = new ArrayList<Map<String, Object>>();
			for (Dictionary dgrp : dict.getChildren()) {// 字典码值或 子字典项目
				Map<String, Object> dictGrp = new LinkedHashMap<String, Object>();
				if (!"3".equals(dgrp.getDictType())) {// 字典子项
					for (Dictionary dv : dgrp.getChildren()) {
						Map<String, Object> dictVal = new LinkedHashMap<String, Object>();
						dictVal.put("key", dv.getDictCode());
						dictVal.put("value", dv.getDictName());
						dictGrp.put("children", dictVal);
					}
				}
				dictGrp.put("key", dgrp.getDictCode());
				dictGrp.put("value", dgrp.getDictName());
				dictGrp.put("desc", dgrp.getDictDesc());
				dictGrp.put("status", dgrp.getStatus());
				dictProj.add(dictGrp);
			}
			DictionaryUtil.dictlists.put(dict.getDictCode(), dictProj);
		}
	}

	/**
	 * 迭代初始化字典项
	 * 
	 * @param parent
	 * @return
	 */
	private void initChildren(Dictionary parent) {
		if (!"3".equals(parent.getDictType())) {
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
