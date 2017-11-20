package com.sshs.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryUtil {
	/**
	 * 字典下拉列表缓存
	 */
	public static Map<String, List<Map<String, Object>>> dictlists = new HashMap<String, List<Map<String, Object>>>();

	/**
	 * 
	 * @param dictCode
	 * @return
	 */
	public static List<Map<String, Object>> getList(String dictCode) {
		return dictlists.get(dictCode);
	}
}
