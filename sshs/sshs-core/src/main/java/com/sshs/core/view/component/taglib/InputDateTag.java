package com.sshs.core.view.component.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

import com.sshs.core.util.UuidUtil;
import com.sshs.core.view.component.Component;

/**
 * 表单日期组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
public class InputDateTag extends BaseTag implements Component {

	/**
	 * 日期组件/标签
	 */
	private static final long serialVersionUID = 1L;

	public String format = "yyyy-MM-dd";

	public void init() {
		this.setId(UuidUtil.get32UUID());
	}

	public String forStartTag() {
		// TODO Auto-generated method stub
		return "";
	}

	/**
	*
	 */
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append(this.forTagBefore(this.id));
		/*
		 * text.append("<div id=\"" + this.getId() +
		 * "_picker\" class=\"date form_date input-group\" data-date=\"\"  data-date-format=\""
		 * + this.getFormat() + "\"  data-link-field=\"" + this.getId() +
		 * "\" data-link-format=\"" + this.getFormat() + "\">");
		 */
		if (required) {
			text.append("<div class=\"input-group-addon\" style=\"color:red\">*</div>");
		}
		text.append("<input type=\"text\" class=\"x-edit date form-control\" id=\"" + this.getId() + "\" name=\""
				+ this.getName() + "\" data-date-format=\"" + this.getFormat() + "\"  placeholder=\""
				+ this.getPlaceholder() + "\"/>");
		/*
		 * text.append("<input type=\"hidden\" id=\"" + this.getId() + "\" name=\"" +
		 * this.getName() + "\" value=\"\" />"); text.
		 * append("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-calendar\"></span></span>"
		 * );
		 */
		if (required) {
			text.append("</div>");
		}
		text.append("<script type=\"text/javascript\">_InitDatePicker(\"" + this.getId() + "\",\"" + this.getFormat()
				+ "\");</script>");
		text.append(super.forTagEnd());
		return text.toString();
	}

	@Override
	public int doStartTag() throws JspException {
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
		if (StringUtils.isNotEmpty(format)) {
			this.format = format;
		}
	}

}