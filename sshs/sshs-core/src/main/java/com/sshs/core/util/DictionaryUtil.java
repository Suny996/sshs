package com.sshs.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典工具类
 * 
 * @author Suny
 * @date 2017-11-20
 */
public class DictionaryUtil {
	/**
	 * 字典下拉列表缓存
	 */
	public static Map<String, List<Map<String, Object>>> dictlists = new HashMap<String, List<Map<String, Object>>>();

	/**
	 * 
	 */
	public static final String DICTIONARY_DESC_SUFFIX = "_desc";

	/**
	 * 
	 * @param dictCode
	 * @return
	 */
	public static List<Map<String, Object>> getList(String dictCode) {
		return dictlists.get(dictCode);
	}
}
