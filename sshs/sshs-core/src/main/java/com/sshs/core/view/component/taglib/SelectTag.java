package com.sshs.core.view.component.taglib;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

import com.sshs.core.util.DictionaryUtil;
import com.sshs.core.util.UuidUtil;
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

	public void init() {
		this.setId(UuidUtil.get32UUID());
	}

	public String forStartTag() {
		return "";
	}

	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append(this.forTagBefore(this.id));
		if (required) {
			text.append("<div class=\"input-group\">\n");
			text.append("<div class=\"input-group-addon\" style=\"color:red\">*</div>");
		}
		text.append("<select type=\"text\" class=\"form-control x-edit\" id=\"" + super.getId() + "\" name=\""
				+ super.getName() + "\" placeholder=\"" + super.getPlaceholder() + "\">");
		if (StringUtils.isNotEmpty(this.getDictCode())) {
			List<Map<String, Object>> list = DictionaryUtil.getList(this.getDictCode());
			if (list != null) {
				for (Map<String, Object> dict : list) {
					if (dict.get("children") != null) {
						text.append("<optgroup label=\"" + dict.get("value") + "\" data-subtext=\"" + dict.get("desc")
								+ "\" class=\"get-class\">");
					} else {
						text.append("<option value=\"" + dict.get("key") + "\">" + dict.get("value") + "</option>");
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