package com.sshs.core.view.component.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

import com.sshs.core.view.component.Component;

/**
 * bootstrapTable表格组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
public class TableBootstrapTag extends BaseTag implements Component {
	/**
	 * bootstrapTable表格组件/标签
	 */
	private static final long serialVersionUID = 1L;
	public String url;
	public String striped;
	public String uniqueId;
	public String pagination;
	public String showToggle;

	@Override
	public String forStartTag() {
		StringBuffer text = new StringBuffer();
		text.append("<table id=\"" + this.id + "\" class=\"table table-bordered table-hover \"" + this.className
				+ " data-url=\"" + this.url + "\"");
		if (StringUtils.isNotEmpty(this.striped)) {
			text.append(" data-striped=" + this.striped + " ");
		}
		if (StringUtils.isNotEmpty(this.pagination)) {
			text.append(" data-pagination=" + this.pagination + " ");
		}
		if (StringUtils.isNotEmpty(this.showToggle)) {
			text.append(" data-show-toggle=" + this.showToggle + " ");
		}
		if (StringUtils.isNotEmpty(this.uniqueId)) {
			text.append(" data-unique-id=" + this.uniqueId + " ");
		}
		text.append(" " + element.attributes() + ">\n");
		return text.toString();
	}

	@Override
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append("</table>");
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStriped() {
		return striped;
	}

	public void setStriped(String striped) {
		this.striped = striped;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public String getShowToggle() {
		return showToggle;
	}

	public void setShowToggle(String showToggle) {
		this.showToggle = showToggle;
	}
}