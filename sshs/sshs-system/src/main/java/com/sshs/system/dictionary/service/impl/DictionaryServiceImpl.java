package com.sshs.system.dictionary.service.impl;

import com.sshs.constant.Global;
import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.page.Page;
import com.sshs.core.util.DictionaryUtil;
import com.sshs.system.dictionary.dao.DictionaryDao;
import com.sshs.system.dictionary.model.Dictionary;
import com.sshs.system.dictionary.model.DictionaryI18n;
import com.sshs.system.dictionary.service.IDictionaryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 数据字典管理服务类 .
 * 
 * @author Suny
 * @date 2017-12-01
 */
@Service
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary> implements IDictionaryService {
	/**
	 * 字典缓存。
	 */
	public static Map<String, Dictionary> dictionarys = new HashMap<String, Dictionary>(100);

	@Resource(name = "dictionaryDao")
	private DictionaryDao dao;

	@Override
	public int delete(String id) throws Exception {
		return dao.delete(id);
	}

	@Override
	public Dictionary findById(String id) throws Exception {
		return dao.findById(id);
	}

	@Override
	public int save(Dictionary dictionary) throws Exception {
		return dao.save(dictionary);
	}

	@Override
	public int update(Dictionary dictionary) throws Exception {
		return dao.update(dictionary);
	}

	@Override
	public void findForList(Page<Dictionary> page) throws Exception {
		findForPageList("com.afcac.system.dictionary.dao.DictionaryDao.findForPageList", page);
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
	 * 初始化字典列表,系统启动时执行缓存
	 * 
	 * @param dict
	 * @return
	 */
	@PostConstruct
	public void initDictList() {
		List<Dictionary> dictCodes = dao.findAllDictCodes();
		// 字典项
		for (Dictionary dict : dictCodes) {
			initChildren(dict);
			List<Map<String, Object>> dictProj = new ArrayList<Map<String, Object>>();
			// 字典码值或 子字典项目
			for (Dictionary dgrp : dict.getChildren()) {
				Map<String, Object> dictGrp = new LinkedHashMap<String, Object>();
				// 字典子项
				if (!Global.DICTIONARY_DICTTYPE_KEYVALUE.equals(dgrp.getDictType())) {
					for (Dictionary dv : dgrp.getChildren()) {
						Map<String, Object> dictVal = new LinkedHashMap<String, Object>();
						dictVal.put("key", dv.getDictCode());
						dictVal.put("value", dv.getDictName());
						dictVal.put("name", dv.getDictName());
						dictVal.put("desc", dv.getDictDesc());
						dictVal.put("status", dv.getStatus());
						if (dv.getI18ns() != null && !dv.getI18ns().isEmpty()) {
							for (DictionaryI18n i18n : dv.getI18ns()) {
								dictVal.put(i18n.getLanguage() + Global.CHARACTER_UNDERLINE + i18n.getCountry(),
										i18n.getDictName());
								dictVal.put(i18n.getLanguage() + Global.CHARACTER_UNDERLINE + i18n.getCountry()
										+ DictionaryUtil.DICTIONARY_DESC_SUFFIX, i18n.getDictDesc());
							}
						}
						dictGrp.put("children", dictVal);
					}
				}
				dictGrp.put("key", dgrp.getDictCode());
				dictGrp.put("value", dgrp.getDictName());
				dictGrp.put("desc", dgrp.getDictDesc());
				dictGrp.put("status", dgrp.getStatus());
				if (dgrp.getI18ns() != null && !dgrp.getI18ns().isEmpty()) {
					for (DictionaryI18n i18n : dgrp.getI18ns()) {
						dictGrp.put(i18n.getLanguage() + Global.CHARACTER_UNDERLINE + i18n.getCountry(),
								i18n.getDictName());
						dictGrp.put(i18n.getLanguage() + Global.CHARACTER_UNDERLINE + i18n.getCountry()
								+ DictionaryUtil.DICTIONARY_DESC_SUFFIX, i18n.getDictDesc());
					}
				}
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
		if (!Global.DICTIONARY_DICTTYPE_KEYVALUE.equals(parent.getDictType())) {
			List<Dictionary> children = dao.findByParentId(parent.getDictId());
			if (children != null && !children.isEmpty()) {
				for (Dictionary d : children) {
					initI18n(d);
					initChildren(d);
					parent.addChild(d);
				}
			}
		}
	}

	/**
	 * 迭代初始化字典项
	 * 
	 * @param parent
	 * @return
	 */
	private void initI18n(Dictionary dict) {
		List<DictionaryI18n> i18ns = dao.findI18nByDictId(dict.getDictId());
		if (i18ns != null && !i18ns.isEmpty()) {
			for (DictionaryI18n i : i18ns) {
				dict.addI18n(i);
			}
		}
	}
}
