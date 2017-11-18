package com.sshs.core.view.component.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

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
	public String id;
	public String name;
	public String style;
	public String label = "";
	public String labelStyle;
	public String placeholder = "";
	public boolean required = false;
	public int columns = 4;

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(id)) {
			this.setId(UuidUtil.get32UUID());
		}
		return super.doStartTag();
	}

	/**
	 * 处理公共部分开始
	 * 
	 * @return
	 */
	public String forTagBefore() {
		StringBuffer text = new StringBuffer();
		text.append("<div class=\"" + getColumnsClass(columns) + " form-inline\">\n");
		text.append("<div class=\"form-group\">\n");
		if (StringUtils.isNotEmpty(label)) {
			text.append("<label for=\"" + id + "\" class=\"control-label\"	style=\"white-space:nowrap;\">" + label
					+ ":</label>\n");
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
		text.append("</div>\n");
		text.append("</div>\n");
		return text.toString();
	}

	@Override
	public void release() {
		super.release();
	}

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

	public void setId(String id) {
		this.id = id;
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

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		if (columns > 12) {
			columns = 12;
		}
		if (columns < 1) {
			columns = 1;
		}
		this.columns = columns;
	}

	/**
	 * 获取布局样式
	 * 
	 * @param columns
	 * @return
	 */
	private String getColumnsClass(int columns) {
		int cols = 12 / columns;
		int mcolumns = 12 / (cols <= 1 ? 1 : cols - 1);
		int scolumns = 12 / (cols <= 2 ? 1 : cols - 2);
		int xcolumns = 12 / (cols <= 3 ? 1 : cols - 3);

		return "col-xs-" + xcolumns + " col-sm-" + scolumns + " col-md-" + mcolumns + " col-lg-" + columns;
	}
}