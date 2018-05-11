package com.sshs.core.view.component.taglib;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.sshs.constant.Global;
import com.sshs.core.util.DictionaryUtil;
import com.sshs.core.view.component.ViewComponent;

/**
 * 表单select组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
@Component("select")
public class SelectTag extends BaseTag implements ViewComponent {

	/**
	 * 输入域组件/标签
	 */
	private static final long serialVersionUID = 1L;

	public String dictCode = "";
	public String showSearch = "true";

	@Override
	public String forStartTag() {
		return "";
	}

	@Override
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append(this.forTagBefore(this.id));

		text.append("<select type=\"select2\" class=\"x-edit\" id=\"" + super.getId() + "\" name=\"" + super.getName()
				+ "\" placeholder=\"" + labelResource.getLabel(this.getPlaceholder()) + "\" ");

		if (StringUtils.isNotEmpty(this.label)) {
			text.append(" label='" + labelResource.getLabel(this.label) + "'  ");
		}

		if (StringUtils.isNotEmpty(this.required) && !Global.CHARACTER_FALSE.equalsIgnoreCase(this.required)) {
			text.append(" required=true");
		}

		if (StringUtils.isNotEmpty(this.readOnly) && !Global.CHARACTER_FALSE.equalsIgnoreCase(this.readOnly)) {
			text.append(" readOnly=true");
		}

		if (StringUtils.isNotEmpty(this.ignore) && !Global.CHARACTER_FALSE.equalsIgnoreCase(this.ignore)) {
			text.append(" ignore=true ");
		}

		if (StringUtils.isNotEmpty(this.showSearch) && Global.CHARACTER_FALSE.equalsIgnoreCase(this.showSearch)) {
			text.append(" data-show-search='-1' ");
		}

		if (StringUtils.isNotEmpty(this.style)) {
			text.append(" style=\"" + this.style + "\" ");
		}
		text.append(" " + getExtAttributesHtml() + ">");

		if (StringUtils.isEmpty(required) || !Global.CHARACTER_FALSE.equalsIgnoreCase(required)) {
			text.append("<option value=\"\">" + labelResource.getLabel("select.nullOption") + "</option>");
		}
		if (StringUtils.isNotEmpty(this.getDictCode())) {
			List<Map<String, Object>> list = DictionaryUtil.getList(this.getDictCode());
			if (list != null) {
				for (Map<String, Object> dict : list) {
					String key = (String) dict.get("key");
					String value = (String) dict.get(labelResource.getLocale().toString());
					if (StringUtils.isEmpty(value)) {
						value = (String) dict.get("value");
					}
					if (dict.get("children") != null) {
						String desc = (String) dict.get(labelResource.getLocale() + "_desc");
						if (StringUtils.isEmpty(desc)) {
							desc = (String) dict.get("desc");
						}
						text.append(
								"<optgroup label=\"" + value + "\" data-subtext=\"" + desc + "\" class=\"get-class\">");
					} else {
						if (StringUtils.isNotEmpty(this.defaultValue) && key.equals(this.defaultValue)) {
							text.append(
									"<option value=\"" + key + "\" selected=true>" + key + " " + value + "</option>");
						} else {
							text.append("<option value=\"" + key + "\">" + key + " " + value + "</option>");
						}
					}
				}
			}
		}
		text.append("</select>");

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

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getShowSearch() {
		return showSearch;
	}

	public void setShowSearch(String showSearch) {
		this.showSearch = showSearch;
	}

}