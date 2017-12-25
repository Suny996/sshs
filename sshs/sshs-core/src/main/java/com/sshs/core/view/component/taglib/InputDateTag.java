package com.sshs.core.view.component.taglib;

import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

import com.sshs.core.locale.LabelResource;
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

	public String format = "";

	@Override
	public void init(LabelResource labelResource) {
		super.init(labelResource);
		if (labelResource.getLocale().equals(Locale.CHINA)) {
			this.format = "yyyy-MM-dd";
		} else {
			this.format = "MM-dd-yyyy";
		}
	}

	@Override
	public String forStartTag() {
		return "";
	}

	/**
	*
	 */
	@Override
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append(this.forTagBefore(this.id));
		String xedit = "";
		if (!this.hasAddon()) {
			xedit = this.xeditClass;
		}
		text.append("<input type=\"text\" class=\"" + xedit + "  date form-control\" id=\"" + this.getId()
				+ "\" name=\"" + this.getName() + "\" data-date-format=\"" + this.getFormat() + "\"  placeholder=\""
				+ this.getPlaceholder() + "\" ");
		if (StringUtils.isNotEmpty(this.defaultAddonValue)) {
			text.append(" preAddonValue='" + this.defaultAddonValue + "'  ");
		}
		text.append(element.attributes() + "/>");

		text.append("<script type=\"text/javascript\">_InitDatePicker(\"" + this.getId() + "\",\"" + this.getFormat()
				+ "\",\"" + labelResource.getLocale() + "\"); </script>");
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