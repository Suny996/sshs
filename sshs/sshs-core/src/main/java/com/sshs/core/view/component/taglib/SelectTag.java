package com.sshs.core.view.component.taglib;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

import com.sshs.core.util.DictionaryUtil;
import com.sshs.core.view.component.Component;

/**
 * 表单select组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
public class SelectTag extends BaseTag implements Component {

	/**
	 * 输入域组件/标签
	 */
	private static final long serialVersionUID = 1L;

	public String dictCode = "";

	@Override
	public String forStartTag() {
		return "";
	}

	@Override
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append(this.forTagBefore(this.id));
		if (required) {
			text.append("<div class=\"input-group\">\n");
			text.append("<div class=\"input-group-addon\" style=\"color:red\">*</div>");
		}
		text.append("<select type=\"text\" class=\"x-edit\" id=\"" + super.getId() + "\" name=\"" + super.getName()
				+ "\" placeholder=\"" + super.getPlaceholder() + "\" " + element.attributes() + ">");
		if (!required) {
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
						text.append("<option value=\"" + key + "\">" + key + " " + value + "</option>");
					}
				}
			}
		}
		text.append("</select>");
		if (required) {
			text.append("</div>\n");
		}

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

}