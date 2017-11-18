package com.sshs.core.view.component.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.sshs.core.view.component.Component;

/**
 * 表单日期组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
@org.springframework.stereotype.Component("input.date")
public class InputDateTag extends BaseTag implements Component {

	/**
	 * 日期组件/标签
	 */
	private static final long serialVersionUID = 1L;

	public String format = "yyyy-MM-dd";

	public void init() {
		// TODO Auto-generated method stub

	}

	public String forStartTag() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append(super.forTagBefore());

		text.append("<div class=\"date form_date input-group\" data-date=\"\" data-date-format=\""
				+ this.format
				+ "\"  data-link-field=\""
				+ this.id
				+ "\" data-link-format=\"" + this.format + "\">");
		if (required) {
			text.append("<div class=\"input-group-addon\" style=\"color:red\">*</div>");
		}
		text.append("<input type=\"text\" class=\"form-control\" id=\"cld"
				+ this.getId() + "\" name=\"cld" + super.getName()
				+ "\" placeholder=\"" + this.getPlaceholder() + "\">");
		text.append("<input type=\"hidden\" id=\"" + this.id + "\" name=\""
				+ this.name + "\" value=\"\" />");
		text.append("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-calendar\"></span></span>");
		text.append("</div>");
		text.append(super.forTagEnd());
		return text.toString();
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doStartTag();
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

	@Override
	public void release() {
		super.release();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}