package com.sshs.core.view.component.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

import com.sshs.core.locale.LabelResource;
import com.sshs.core.view.component.Component;

/**
 * 表单普通输入域组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
public class ButtonQueryTag extends BaseTag implements Component {
	/**
	 * 输入域组件/标签
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(LabelResource labelResource) {
		super.init(labelResource);
		this.className = "btn-primary";
		this.icon = "linear linear-magnifier";
		this.value = "search";
		this.accessKey = "s";
	}

	public String icon;
	public String onclick;
	public String accessKey;

	@Override
	public String forStartTag() {
		StringBuffer text = new StringBuffer();
		text.append("<button type=\"submit\" class=\"x-btn btn " + this.className + "\" id=\"" + this.getId() + "\"");
		if (StringUtils.isNotEmpty(this.name)) {
			text.append(" name=\"" + this.getName() + "\"");
		}
		if (StringUtils.isNotEmpty(this.onclick)) {
			text.append(" onclick=\"" + this.onclick + "\"");
		}
		if (StringUtils.isNotEmpty(this.accessKey)) {
			text.append(" accessKey=\"" + this.accessKey + "\"");
		}
		text.append(getExtAttributesHtml() +">");
		if (StringUtils.isNotEmpty(this.icon)) {
			text.append("<i class=\"" + this.icon + "\"> </i>");
		}
		return text.toString();
	}

	@Override
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append(labelResource.getLabel(this.value));
		text.append("</button>");
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		if (StringUtils.isNotEmpty(icon)) {
			this.icon = icon;
		}
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	@Override
	public void setClassName(String className) {
		if (StringUtils.isNotEmpty(className)) {
			this.className = className;
		}
	}

	@Override
	public void setValue(String value) {
		if (StringUtils.isNotEmpty(value)) {
			this.value = value;
		}
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		if (StringUtils.isNotEmpty(accessKey)) {
			this.accessKey = accessKey;
		}
	}
}