package com.sshs.core.view.component.taglib;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;

import com.sshs.core.customise.model.Customise;
import com.sshs.core.util.UuidUtil;
import com.sshs.core.view.component.Component;

/**
 * 表单查询条件组件/标签
 * 
 * @author Suny
 * @date 2017-10-17
 * 
 */
public class FormConditionTag extends BaseTag implements Component {
	/**
	 * 输入域组件/标签
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	public void init() {
		this.setId(UuidUtil.get32UUID());
		this.className = "form-inline";
	}

	public String customise;
	public String onclick;
	public String action;

	@Override
	public String forStartTag() {
		StringBuffer text = new StringBuffer();
		if (StringUtils.isNotEmpty(this.customise) && !"false".equalsIgnoreCase(this.customise)) {
			text.append("<ul class=\"nav nav-tabs\" contentId=\"" + this.id + "\" pageId=\""
					+ this.labelResource.getPageId() + "\">");
			text.append(
					"<li class=\"active\"><a href=\"#home\" data-toggle=\"tab\" class=\"tab-button-default\"  contentId=\""
							+ this.id + "\" show-elements=[] >默认条件</a></li>");

			Customise custom = new Customise();
			custom.setPageId(this.labelResource.getPageId());
			custom.setUserCode("admin");
			List<Customise> customises = sqlSessionTemplate.selectList("com.sshs.core.customise.findCustomiseList",
					custom);
			for (Customise customise : customises) {
				text.append("<li> <a href=\"#home\" data-toggle=\"tab\"  class=\"tab-button-custom\" contentId=\""
						+ this.id + "\"  show-elements=" + customise.getFieldContents() + ">");
				text.append(customise.getCustomiseName());
				text.append("&nbsp;<i class=\"glyphicon glyphicon-minus\"  onclick=\"_delCustomise('"
						+ customise.getCustomiseId() + "','" + customise.getCustomiseName() + "');\"></i></a></li>");
			}
			text.append("<li type=\"add\"><a href=\"javascript:$('#" + this.getName()
					+ "Modal').modal('show');\"  class=\"tab-button-add\" contentId=\"" + this.id
					+ "\" ><i class=\"glyphicon glyphicon-plus\"></i></a></li>");
			text.append("</ul>");

			text.append(this.getCustomiseConditionDialog());
		}

		text.append("<form class=\"" + this.className + "\" id=\"" + this.getId() + "\"");
		if (StringUtils.isNotEmpty(this.name)) {
			text.append(" name=\"" + this.getName() + "\"");
		}
		if (StringUtils.isNotEmpty(this.onclick)) {
			text.append(" onclick=\"" + this.onclick + "\"");
		}
		if (StringUtils.isNotEmpty(this.action)) {
			text.append(" action=\"" + this.action + "\"");
		}
		text.append(getExtAttributesHtml() + ">");

		return text.toString();
	}

	@Override
	public String forEndTag() {
		StringBuffer text = new StringBuffer();
		text.append("</form>");

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

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCustomise() {
		return customise;
	}

	public void setCustomise(String customise) {
		this.customise = customise;
	}

	/**
	 * 生成自定义查询条件 添加框
	 * 
	 * @return
	 */
	public String getCustomiseConditionDialog() {
		StringBuffer text = new StringBuffer(" ");
		text.append("<div id=\"" + this.getName() + "Modal\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\">");
		text.append("<div class=\"modal-dialog\" role=\"document\">");
		text.append("<div class=\"modal-content\">");
		text.append("<div class=\"modal-header\">");
		text.append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">");
		text.append("	<span aria-hidden=\"true\">&times;</span>");
		text.append("	</button>");
		text.append("<h4 class=\"modal-title\" id=\"ModalLabel\">添加自定义查询</h4>");
		text.append("</div>");
		text.append("<div class=\"modal-body\" style=\"height: 350px; overflow: visible;\">");

		text.append(
				"<div class=\"appearance x-label-edit x-label30 col-xs-12 col-sm-12 col-md-12 col-lg-12\" name=\"tabName-Container\">\r\n"
						+ "<label for=\"customisetabName\" class=\"x-label x-right control-label\"	style=\"white-space:nowrap;\">"
						+ labelResource.getLabel("customisetabName") + ":</label>\r\n"
						+ "<input type=\"text\" class=\"x-edit form-control \" required=true id=\"customisetabName\" name=\"customisetabName\" placeholder=\""
						+ labelResource.getLabel("customisetabName") + "\" label='"
						+ labelResource.getLabel("customisetabName") + "'   /> \r\n" + "</div>");
		text.append("<div class=\"doublebox-container\" dialog=\"" + this.getName() + "Modal\" contentId=\"" + this.id
				+ "\">");
		text.append("<select multiple=\"multiple\" size=\"15\" name=\"doublebox\"  class=\"customiseBox\">");
		text.append("	</select>");
		text.append("			</div>");
		text.append("	</div>");
		text.append("<div class=\"modal-footer\">");
		text.append("	<button type=\"button\" class=\"btn btn-primary\" onclick=\"_addCustomise($('ul[contentId=\\'"
				+ this.id + "\\']'),$('.doublebox-container[contentId=\\'" + this.id + "\\']'));\">"
				+ this.labelResource.getLabel("ok") + "</button>");
		text.append("	<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">"
				+ this.getLabelResource().getLabel("close") + "</button>");
		text.append("		</div>");
		text.append("		</div>");
		text.append("		<!-- /.modal-content -->");
		text.append("	</div>");
		text.append("	<!-- /.modal-dialog -->");
		text.append("		</div>");
		text.append("	<!-- /.modal -->");

		return text.toString();
	}
}