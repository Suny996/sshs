package com.sshs.core.view.component;

import org.jsoup.nodes.Element;

import com.sshs.core.locale.LabelResource;

/**
 * 页面组件接口
 * 
 * @author Suny
 * @date 2017-11-02
 */
public interface Component {
	/**
	 * ID
	 * 
	 * @return
	 */
	String getId();

	/**
	 * 标签尾部
	 * 
	 * @return
	 */
	String forEndTag();

	/**
	 * 标签头部
	 * 
	 * @return
	 */
	String forStartTag();

	/**
	 * 初始化
	 * 
	 * @param labelResource
	 */
	void init(LabelResource labelResource);

	/**
	 * 将原始元素传入
	 * 
	 * @param element
	 */
	void setElement(Element element);
}
