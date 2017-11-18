package com.sshs.core.view.component.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.sshs.core.view.component.Component;

/**
 * 表单普通输入域组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
public class InputTextTag extends BaseTag implements Component {

	public void init() {
		// TODO Auto-generated method stub
	}

	/**
	 * 输入域组件/标签
	 */
	private static final long serialVersionUID = 1L;

	public String forStartTag() {
		return "";
	}

	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append(super.forTagBefore());
		text.append("<div class=\"input-group\">\n");
		if (required) {
			text.append("<div class=\"input-group-addon\" style=\"color:red\">*</div>");
		}
		text.append("<input type=\"text\" class=\"form-control\" id=\""
				+ super.getId() + "\" name=\"" + super.getName()
				+ "\" placeholder=\"" + super.getPlaceholder() + "\">");
		text.append("</div>");
		text.append(super.forTagEnd());
		return text.toString();
	}

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