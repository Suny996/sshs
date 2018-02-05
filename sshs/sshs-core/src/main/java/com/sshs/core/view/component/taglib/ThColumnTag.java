package com.sshs.core.view.component.taglib;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.sshs.constant.Global;
import com.sshs.core.util.DictionaryUtil;
import com.sshs.core.view.component.ViewComponent;
import com.sshs.core.view.resolver.ViewResolver;

import net.sf.json.JSONArray;

/**
 * bootstrapTable th-head表格组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
@Component("thColumn")
public class ThColumnTag extends BaseTag implements ViewComponent {
	/**
	 * bootstrapTable表格组件/标签
	 */
	private static final long serialVersionUID = 1L;
	public String field;
	public String sortable;
	public String format;
	public String formatter;
	public String editable;
	public String dictCode;
	public String width;
	public String rowNumber;
	String scripts = "";

	@Override
	public String forStartTag() {
		StringBuffer text = new StringBuffer();
		text.append("<th data-field=\"" + this.field + "\"");
		if (StringUtils.isNotEmpty(this.sortable)) {
			text.append(" data-sortable=" + this.sortable);
		}
		if (StringUtils.isNotEmpty(this.width)) {
			text.append(" data-width=\"" + this.width + "\" ");
		}
		Elements children = this.getElement().children();
		if (children != null && children.size() > 0) {
			StringBuffer format = new StringBuffer();
			for (Element e : children) {
				String name = e.tagName();
				if (ViewResolver.VIEW_COMPONENT_INPUT.equalsIgnoreCase(name)) {
					format.append(getInputHTML(e));
				}
				if (ViewResolver.VIEW_COMPONENT_SELECT.equalsIgnoreCase(name)) {
					format.append(getSelectHTML(e));
				}
				if (ViewResolver.VIEW_COMPONENT_BUTTON.equalsIgnoreCase(name)) {
					format.append(getButtonHTML(e));
				}
			}
			text.append(" data-format='" + format + "' ");
			text.append(" data-formatter=_EditorRender ");
			children.remove();
		} else if (StringUtils.isNotEmpty(this.rowNumber)) {
			if (StringUtils.isEmpty(this.field)) {
				this.setField("table.rowNumber");
			}
			if (StringUtils.isEmpty(this.formatter)) {
				text.append(" data-formatter=_RowNumber");
			} else {
				text.append(" data-formatter=\"" + this.formatter + "\"");
			}
		} else if (StringUtils.isNotEmpty(this.dictCode)) {
			text.append(" data-format=\"" + this.dictCode + "\"");
			if (StringUtils.isEmpty(this.formatter)) {
				text.append(" data-formatter=_DictTranslate");
			} else {
				text.append(" data-formatter=\"" + this.formatter + "\"");
			}

			List<Map<String, Object>> list = DictionaryUtil.getList(this.getDictCode());
			JSONArray dictList = JSONArray.fromObject(list);
			scripts = "<script type=\"text/javascript\">_DICTIONNARY[\"" + this.getDictCode() + "\"]=" + dictList
					+ "; </script>";
		} else {
			scripts = "";
			if (StringUtils.isNotEmpty(this.format)) {
				text.append(" data-format=\"" + this.format + "\"");
			}
			if (StringUtils.isNotEmpty(this.formatter)) {
				text.append(" data-formatter=\"" + this.formatter + "\"");
			}
		}
		text.append(getExtAttributesHtml() + ">\n");
		return text.toString();
	}

	@Override
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append(labelResource.getLabel(this.field));
		text.append(scripts);
		text.append("</th>");
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

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	/**
	 * 处理Input
	 * 
	 * @param element
	 * @return
	 */
	private String getInputHTML(Element element) {
		StringBuffer text = new StringBuffer();
		switch (element.attr("type")) {
		case "checkbox":
			text.append("<input type=\"checkbox\"  class=\"form-control\" ");
			break;
		case "switch":
			String labelText = "";
			if (StringUtils.isNotEmpty(element.attr(ViewResolver.VIEW_COMPONENT_TEXT))) {
				labelText = Global.CHARACTER_DOT + element.attr(ViewResolver.VIEW_COMPONENT_TEXT);
			}
			text.append("<input type=\"checkbox\" class=\"form-switch form-control \" data-on-text=\""
					+ labelResource.getLabel("switch.dataOnText" + labelText) + "\" data-off-text= \""
					+ labelResource.getLabel("switch.dataOffText" + labelText) + "\" ");

			if (StringUtils.isNotEmpty(element.attr(ViewResolver.CHARACTER_DEFAULTVALUE))) {
				text.append(" defaultValue=" + element.attr(ViewResolver.CHARACTER_DEFAULTVALUE));
			}
			break;
		default:
			text.append("<input type=\"text\" class=\"form-control\" ");
		}
		text.append(" name=\"" + this.field + "\"");
		for (Attribute a : element.attributes()) {
			if (!a.getKey().equalsIgnoreCase("type")) {
				text.append(" " + a.getKey() + "=\"" + a.getValue() + "\" ");
			}
		}
		text.append("></input>");
		return text.toString();
	}

	/**
	 * 处理Select
	 * 
	 * @param element
	 * @return
	 */
	private String getSelectHTML(Element element) {
		StringBuffer text = new StringBuffer();
		text.append(
				"<select type=\"text\" class=\" form-select2 x-edit\"  name=\"" + this.field + "\" width=\"100%\">");
		if (StringUtils.isEmpty(required) || Global.CHARACTER_FALSE.equalsIgnoreCase(required)) {
			text.append("<option value=\"\">" + labelResource.getLabel("select.nullOption") + "</option>");
		}
		if (StringUtils.isNotEmpty(element.attr(ViewResolver.VIEW_COMPONENT_PLISTKEY))) {
			List<Map<String, Object>> list = DictionaryUtil.getList(element.attr(ViewResolver.VIEW_COMPONENT_PLISTKEY));
			if (list != null) {
				for (Map<String, Object> dict : list) {
					String key = (String) dict.get("key");
					String value = (String) dict.get(labelResource.getLocale().toString());
					if (StringUtils.isEmpty(value)) {
						value = (String) dict.get("value");
					}
					if (dict.get("children") != null) {
						String desc = (String) dict
								.get(labelResource.getLocale() + DictionaryUtil.DICTIONARY_DESC_SUFFIX);
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
		return text.toString();
	}

	/**
	 * 处理Button
	 * 
	 * @param element
	 * @return
	 */
	private String getButtonHTML(Element element) {
		StringBuffer text = new StringBuffer();
		String labelText = "";
		if (StringUtils.isNotEmpty(element.attr(ViewResolver.VIEW_COMPONENT_TEXT))) {
			labelText = element.attr(ViewResolver.VIEW_COMPONENT_TEXT);
			element.removeAttr(ViewResolver.VIEW_COMPONENT_TEXT);
		}
		text.append("<button name=\"" + this.field + "\" ");
		if (StringUtils.isNotEmpty(element.attr(ViewResolver.VIEW_COMPONENT_CLASS))) {
			text.append(" class=\"btn btn-link " + element.attr(ViewResolver.VIEW_COMPONENT_CLASS) + "\" ");
			element.removeAttr(ViewResolver.VIEW_COMPONENT_CLASS);
		} else {
			text.append(" class=\"btn btn-link \"");
		}

		if (StringUtils.isNotEmpty(element.attr(ViewResolver.VIEW_COMPONENT_ACTION))) {
			text.append(" action=" + element.attr(ViewResolver.VIEW_COMPONENT_ACTION) + " ");
			element.removeAttr(ViewResolver.VIEW_COMPONENT_ACTION);
		}
		for (Attribute a : element.attributes()) {
			if (!a.getKey().equalsIgnoreCase("type")) {
				text.append(a.getKey() + "=\"" + a.getValue() + "\" ");
			}
		}

		text.append(">" + labelResource.getLabel(labelText) + "</button>");
		return text.toString();
	}
}