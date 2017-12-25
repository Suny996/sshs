package com.sshs.core.view.component.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

import com.sshs.core.view.component.Component;

/**
 * 表单普通输入域组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
public class InputTextTag extends BaseTag implements Component {
	/**
	 * 输入域组件/标签
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String forStartTag() {
		return "";
	}

	@Override
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append(this.forTagBefore(this.id));
		String xedit = "";
		if (!this.hasAddon()) {
			xedit = this.xeditClass;
		}
		text.append("<input type=\"text\" class=\"" + xedit + " form-control \" id=\"" + this.getId() + "\" name=\""
				+ this.getName() + "\" placeholder=\"" + this.getPlaceholder() + "\"");
		if (StringUtils.isNotEmpty(this.defaultAddonValue)) {
			text.append(" preAddonValue='" + this.defaultAddonValue + "'  ");
		}
		text.append(getExtAttributesHtml() + "/>");
		text.append(super.forTagEnd());
		return text.toString();
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(forEndTag());
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_PAGE;
	}

}