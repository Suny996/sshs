package com.sshs.core.view.component.taglib;

import java.util.Locale;

import javax.inject.Named;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;

import com.sshs.core.locale.LabelResource;
import com.sshs.core.util.UuidUtil;

/**
 * 表单普通输入域标签
 * 
 * @author Suny
 * @date 2017-10-17
 *
 */
public abstract class BaseTag extends TagSupport {

	/**
	 * 输入域标签
	 */
	private static final long serialVersionUID = 1L;
	public static final int VIEW_MAX_COLUMNS = 12;
	public static final int VIEW_DEFAULT_COLUMNS = 4;
	public Locale locale;
	public LabelResource labelResource;
	public Element element;
	public String id;
	public String name;
	public String style;
	public String label = "";
	public String labelStyle;
	public String placeholder = "";
	public String required = "false";
	public String defaultValue;
	public String preAddon;
	public String postAddon;
	public String defaultAddonValue;
	public String xeditClass = "xedit";

	public String value;

	@Named("class")
	public String className = "";

	public String columns;

	public void init(LabelResource labelResource) {
		this.setId(UuidUtil.get32UUID());
		this.labelResource = labelResource;
		this.locale = labelResource.getLocale();
		this.defaultAddonValue = null;
		this.defaultValue = null;
	}

	@Override
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	/**
	 * 处理公共部分开始
	 * 
	 * @return
	 */
	public String forTagBefore(String id) {
		StringBuffer text = new StringBuffer();
		text.append("<div class=\"appearance x-label-edit x-label30 " + getColumnsClass(columns) + "\">\n");
		if (StringUtils.isEmpty(label)) {
			label = this.name;
		}
		label = labelResource.getLabel(label);
		text.append("<label for=\"" + id + "\" class=\"x-label x-right control-label\"	style=\"white-space:nowrap;\">"
				+ label + ":" + getRequiredHtml() + "</label>\n");
		if (this.hasAddon()) {
			text.append("<div class=\"x-edit input-group\">");
		}
		if (StringUtils.isNotEmpty(this.preAddon)) {
			if (this.preAddon.startsWith("select")) {
				String defaultAddonValue = "";
				if (this.preAddon.indexOf("select.") >= 0) {
					defaultAddonValue = this.preAddon.substring(this.preAddon.indexOf('.') + 1,
							this.preAddon.indexOf(','));
					this.defaultAddonValue = defaultAddonValue;
				} else {
					defaultAddonValue = this.preAddon.substring(this.preAddon.indexOf(',') + 1,
							this.preAddon.indexOf(',', this.preAddon.indexOf(',') + 1));
					this.defaultAddonValue = null;
				}

				String optionStr = this.preAddon.substring(this.preAddon.indexOf(',') + 1);
				String[] options = optionStr.split(",");
				text.append("<div class=\"input-group-btn\" style=\"width:1%;\">");
				text.append(
						"<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" "
								+ " aria-expanded=\"false\">");
				text.append("	<span id=\"" + this.id + "preAddonSelectBtn\" style=\"padding-right:3px;\">"
						+ this.labelResource.getLabel("addon.select." + defaultAddonValue, defaultAddonValue)
						+ "</span><span class=\"caret\"></span>");
				text.append("</button>");
				text.append("<ul class=\"dropdown-menu\">");
				for (String option : options) {
					text.append("<li><a href=\"javascript:$('#" + this.id + "preAddonSelectBtn').text('"
							+ this.labelResource.getLabel("addon.select." + option, option) + "');$('#" + this.id
							+ "').attr('preAddonValue','" + option + "');\">"
							+ this.labelResource.getLabel("addon.select." + option, option) + "</a></li>");
				}
				text.append("</ul>");
				text.append("</div>");
			} else {
				text.append("<div class=\"input-group-addon\">");
				text.append(this.getPreAddon());
				text.append("</div>");
			}
		}
		return text.toString();
	}

	/**
	 * 处理公共部分结束
	 * 
	 * @return
	 */
	public String forTagEnd() {
		StringBuffer text = new StringBuffer();
		if (this.hasAddon()) {
			text.append("</div>\n");
		}
		text.append("</div>\n");
		return text.toString();
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasAddon() {
		if (StringUtils.isNotEmpty(this.preAddon) || StringUtils.isNotEmpty(this.postAddon)) {
			return true;
		}
		return false;
	}

	@Override
	public void release() {
		super.release();
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public LabelResource getLabelResource() {
		return labelResource;
	}

	public void setLabelResource(LabelResource labelResource) {
		this.labelResource = labelResource;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStyle() {
		return style;
	}

	public String getLabel() {
		return label;
	}

	public String getLabelStyle() {
		return labelStyle;
	}

	@Override
	public void setId(String id) {
		if (StringUtils.isNotEmpty(id)) {
			this.id = id;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLabelStyle(String labelStyle) {
		this.labelStyle = labelStyle;
	}

	public String getPlaceholder() {
		if (StringUtils.isEmpty(placeholder)) {
			placeholder = label;
		}
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getPreAddon() {
		return preAddon;
	}

	public void setPreAddon(String preAddon) {
		this.preAddon = preAddon;
	}

	public String getPostAddon() {
		return postAddon;
	}

	public void setPostAddon(String postAddon) {
		this.postAddon = postAddon;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	/**
	 * 获取布局样式
	 * 
	 * @param columns
	 * @return
	 */
	public String getColumnsClass(String cs) {
		int columns = VIEW_DEFAULT_COLUMNS;
		try {
			columns = Integer.valueOf(cs);
		} catch (NumberFormatException e) {

		}
		if (columns > VIEW_MAX_COLUMNS) {
			columns = VIEW_MAX_COLUMNS;
		}
		if (columns < 1) {
			columns = 1;
		}
		int cols = VIEW_MAX_COLUMNS / columns;
		int mcolumns = VIEW_MAX_COLUMNS / (cols <= 1 ? 1 : cols - 1);
		int scolumns = VIEW_MAX_COLUMNS / (cols <= 2 ? 1 : cols - 2);
		int xcolumns = VIEW_MAX_COLUMNS / (cols <= 3 ? 1 : cols - 3);

		return "col-xs-" + xcolumns + " col-sm-" + scolumns + " col-md-" + mcolumns + " col-lg-" + columns;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRequiredHtml() {
		if (StringUtils.isNotEmpty(required) && !"false".equalsIgnoreCase(required)) {
			return "<span style=\"font-size:20;color:#FFA042;text-align:right; vertical-align:middle;\">*</span>";
		} else {
			return "";
		}
	}
}