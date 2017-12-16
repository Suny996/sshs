package com.sshs.core.view.resolver;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.sshs.core.locale.LabelResource;
import com.sshs.core.util.ReflectHelper;
import com.sshs.core.util.SpringUtil;
import com.sshs.core.util.UuidUtil;
import com.sshs.core.view.component.Component;

/**
 * @see 解析view
 * @author Suny
 * @date 2017-11-16
 */
public class ViewResolver {

	/**
	 * logger
	 */
	private static Logger logger = Logger.getLogger(ViewResolver.class);

	/**
	 * body
	 */
	public static final String VIEW_PAGE_TYPE_BODY = "body";

	/**
	 * input
	 */
	public static final String VIEW_COMPONENT_INPUT = "input";

	/**
	 * button
	 */
	public static final String VIEW_COMPONENT_BUTTON = "button";

	/**
	 * text
	 */
	public static final String VIEW_COMPONENT_TEXT = "text";

	/**
	 * select
	 */
	public static final String VIEW_COMPONENT_SELECT = "select";

	/**
	 * dictCode
	 */
	public static final String VIEW_COMPONENT_PLISTKEY = "dictCode";

	/**
	 * action
	 */
	public static final String VIEW_COMPONENT_ACTION = "action";

	/**
	 * class
	 */
	public static final String VIEW_COMPONENT_CLASS = "class";

	/**
	 * 解析视图
	 * 
	 * @param input
	 * @param labelResource
	 * @param viewTemplate
	 * @param pageType
	 * @return
	 */
	public static String resolve(InputStream input, LabelResource labelResource, String viewTemplate, String pageType,
			String privateJs) {

		String text = "";
		try {
			Parser parser = Parser.htmlParser();
			parser.settings(new ParseSettings(true, true));
			Document doc = Jsoup.parse(input, "UTF-8", "", parser);

			StringBuffer headText = new StringBuffer();
			StringBuffer bodyText = new StringBuffer();
			Elements bodys = doc.getElementsByTag("body");
			if (bodys != null && bodys.size() > 0) {
				for (Element e : bodys) {
					bodyText.append(resolveBody(e, labelResource));
				}
			}
			bodyText.append(privateJs);// 加载私有js文件
			if (!VIEW_PAGE_TYPE_BODY.equalsIgnoreCase(pageType)) {
				Elements heads = doc.getElementsByTag("head");
				if (heads != null && heads.size() > 0) {
					for (Element e : heads) {
						headText.append(resolveHead(e));
					}
				}
				text = viewTemplate.replace("<!--_PageHeader-->", headText)
						.replace("<!--_PageBody-->",
								"<div id=\"" + UuidUtil.get32UUID() + "\" type=\"page\" class=\"#container-fluid\">"
										+ bodyText + "</div>")
						.replace("<!--_PageFooter-->", "").replace("<!--_PageException-->", "")
						.replaceAll("\\$\\{locale\\}", labelResource.getLocale().toString());
			} else {
				text = bodyText.toString();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return text;
	}

	/**
	 * 
	 * @param body
	 * @return
	 */
	private static String resolveBody(Element body, LabelResource labelResource) {
		StringBuffer text = new StringBuffer();
		text.append(body.ownText());
		Elements elements = body.children();
		for (Element e : elements) {
			text.append(resolveElement(e, labelResource));
		}
		return text.toString();
	}

	/**
	 * 
	 * @param head
	 * @return
	 */
	private static String resolveHead(Element head) {
		StringBuffer text = new StringBuffer();
		text.append(head.children());
		return text.toString();
	}

	/**
	 * 
	 * @param element
	 * @return
	 */
	private static String resolveElement(Element element, LabelResource labelResource) {
		StringBuffer text = new StringBuffer();
		String name = element.tagName();
		String type = element.attr("type");
		if (StringUtils.isEmpty(type)) {
			if (VIEW_COMPONENT_INPUT.equalsIgnoreCase(name)) {
				type = "text";
			}
			if (VIEW_COMPONENT_BUTTON.equalsIgnoreCase(name)) {
				type = "button";
			}
		}
		Object bean = SpringUtil.getComponent(name + ReflectHelper.capitalName(type));
		Component component = null;
		// start
		if (bean != null && bean instanceof Component) {
			component = (Component) bean;
			try {
				initComponent(component, element, labelResource);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String eleText = component.forStartTag();
			logger.debug(eleText);
			text.append(eleText);
		} else {
			text.append("<" + name + element.attributes() + ">\n");
		}
		// body
		text.append(element.data() + element.ownText());
		Elements elements = element.children();
		for (Element e : elements) {
			text.append(resolveElement(e, labelResource));
		}
		// end
		if (component != null) {
			String eleText = component.forEndTag();
			logger.debug(eleText);
			text.append(eleText);
		} else {
			text.append("</" + name + ">\n");
		}

		return text.toString();
	}

	/**
	 * 通过element 初始化component
	 * 
	 * @param comp
	 * @param elment
	 * @return
	 */

	private static Element initComponent(Component component, Element element, LabelResource labelResource) {
		component.init(labelResource);
		Element e = element.clone();
		Field[] fields = component.getClass().getFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			Object value = "";
			String attrName = "";
			if (element.hasAttr(fieldName)) {
				attrName = fieldName;
				value = element.attr(attrName);
			} else {
				Annotation[] annotations = field.getAnnotations();
				if (annotations != null && annotations.length > 0 && annotations[0] instanceof Named) {
					Named named = (Named) annotations[0];
					attrName = named.value();
					if (element.hasAttr(attrName)) {
						value = element.attr(attrName);
					}
				}
			}

			try {
				Method method = component.getClass().getMethod("set" + ReflectHelper.capitalName(fieldName),
						value.getClass());
				try {
					if (method != null) {
						method.invoke(component, value);
						e.removeAttr(attrName);
					}
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
			} catch (NoSuchMethodException e1) {
				logger.error(e1.getMessage());
			} catch (SecurityException e1) {
				logger.error(e1.getMessage());
			}
			component.setElement(element);
		}
		return e;
	}
}
