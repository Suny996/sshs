package com.sshs.core.view.component.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.sshs.core.view.component.ViewComponent;

/**
 * 表单普通输入域组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
@Component("divButtonGroup")
public class ButtonGroupTag extends BaseTag implements ViewComponent {
	/**
	 * 输入域组件/标签
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String forStartTag() {
		StringBuffer text = new StringBuffer();
		text.append("<div class=\"appearance x-button-edit " + getColumnsClass(columns) + "\"");
		if (StringUtils.isNotEmpty(this.style)) {
			text.append(" style=\"" + this.style + "\" ");
		}
		text.append(getExtAttributesHtml() + ">\n");
		return text.toString();
	}

	@Override
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append("</div>");
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