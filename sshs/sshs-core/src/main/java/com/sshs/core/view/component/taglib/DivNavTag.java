package com.sshs.core.view.component.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.sshs.core.util.UuidUtil;
import com.sshs.core.view.component.ViewComponent;

/**
 * 导航-菜单（LAYUI-navbar）组件/标签
 * 
 * @author Suny
 * @date 2018-3-10
 * 
 */
@Component("divNav")
public class DivNavTag extends BaseTag implements ViewComponent {
	/**
	 * 导航-菜单（LAYUI-navbar）组件/标签
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		this.setId(UuidUtil.get32UUID());
		this.className = "layui-nav";
	}

	public String type;
	public String url;
	public String distype;

	@Override
	public String forStartTag() {
		StringBuffer text = new StringBuffer();
		text.append("<div class=\"" + this.className + "\" id=\"" + this.getId() + "\"");

		if (StringUtils.isNotEmpty(this.name)) {
			text.append(" name=\"" + this.getName() + "\"");
		}
		if (StringUtils.isNotEmpty(this.type)) {
			text.append(" data-type=\"" + this.getType() + "\"");
		}
		if (StringUtils.isNotEmpty(this.url)) {
			text.append(" data-url=\"" + this.getUrl() + "\"");
		}
		if (StringUtils.isNotEmpty(this.distype)) {
			text.append(" data-distype=\"" + this.getDistype() + "\"");
		}
		if (StringUtils.isNotEmpty(this.style)) {
			text.append(" style=\"" + this.style + "\" ");
		}
		text.append(getExtAttributesHtml() + ">");
		return text.toString();
	}

	@Override
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		//text.append(labelResource.getLabel(this.value));
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDistype() {
		return distype;
	}

	public void setDistype(String distype) {
		this.distype = distype;
	}
}