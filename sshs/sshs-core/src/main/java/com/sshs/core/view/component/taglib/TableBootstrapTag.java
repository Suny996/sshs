package com.sshs.core.view.component.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.sshs.core.view.component.ViewComponent;

/**
 * bootstrapTable表格组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
@Component("tableBootstrap")
public class TableBootstrapTag extends BaseTag implements ViewComponent {
	/**
	 * bootstrapTable表格组件/标签
	 */
	private static final long serialVersionUID = 1L;
	public String url;
	public String striped;
	public String uniqueId;
	public String pagination;
	public String showToggle;
	public String clickToSelect;
	public String singleSelect;
	public String treeId;
	public String treeField;
	public String parentField;
	public String treeFieldIndex;

	@Override
	public String forStartTag() {
		StringBuffer text = new StringBuffer();
		text.append("<table id=\"" + this.id + "\" class=\"table table-bordered table-hover \"" + this.className
				+ " data-url=\"" + this.url + "\"");
		if (StringUtils.isNotEmpty(this.striped)) {
			text.append(" data-striped=" + this.striped);
		}
		if (StringUtils.isNotEmpty(this.pagination)) {
			text.append(" data-pagination=" + this.pagination);
		}
		if (StringUtils.isNotEmpty(this.showToggle)) {
			text.append(" data-show-toggle=" + this.showToggle);
		}
		if (StringUtils.isNotEmpty(this.uniqueId)) {
			text.append(" data-unique-id=" + this.uniqueId);
		}
		if (StringUtils.isNotEmpty(this.clickToSelect)) {
			text.append(" data-click-to-select=" + this.clickToSelect);
		}
		if (StringUtils.isNotEmpty(this.singleSelect)) {
			text.append(" data-single-select=" + this.singleSelect);
		}
		if (StringUtils.isNotEmpty(this.treeId)) {
			text.append(" data-tree-id=" + this.treeId);
		}
		if (StringUtils.isNotEmpty(this.treeField)) {
			text.append(" data-tree-field=" + this.treeField);
		}
		if (StringUtils.isNotEmpty(this.parentField)) {
			text.append(" data-parent-field=" + this.parentField);
		}
		if (StringUtils.isNotEmpty(this.treeFieldIndex)) {
			text.append(" data-tree-field-index=" + this.treeFieldIndex);
		}
		text.append(getExtAttributesHtml() + ">\n");
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

	public String getClickToSelect() {
		return clickToSelect;
	}

	public void setClickToSelect(String clickToSelect) {
		this.clickToSelect = clickToSelect;
	}

	public String getSingleSelect() {
		return singleSelect;
	}

	public void setSingleSelect(String singleSelect) {
		this.singleSelect = singleSelect;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getTreeField() {
		return treeField;
	}

	public void setTreeField(String treeField) {
		this.treeField = treeField;
	}

	public String getParentField() {
		return parentField;
	}

	public void setParentField(String parentField) {
		this.parentField = parentField;
	}

	public String getTreeFieldIndex() {
		return treeFieldIndex;
	}

	public void setTreeFieldIndex(String treeFieldIndex) {
		this.treeFieldIndex = treeFieldIndex;
	}
}