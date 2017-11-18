package com.sshs.system.coder.helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sshs.core.util.Configure;
import com.sshs.core.util.Freemarker;
import com.sshs.core.util.ReflectHelper;
import com.sshs.system.coder.model.Coder;
import com.sshs.system.coder.model.Column;

public class CodeGenerator {
	public static void generate(Coder coder) throws Exception {
		CodeGenerator.processProperties(coder);
		String xml = Freemarker.printFreemarkerString("config.xml", CodeGenerator.toMap(coder));
		Document document = null;
		SAXReader reader = null;
		reader = new SAXReader();
		document = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));

		Element root = document.getRootElement();
		@SuppressWarnings("unchecked")
		Iterator<Element> iterator = root.elementIterator();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			String packageName = element.element("packageName").getText();
			String className = element.element("className").getText();
			String templateFileName = element.element("template").getText();
			File dir = new File(packageName);
			if (!dir.exists())
				dir.mkdirs();
			String outFileName = Configure.getProperty("coder.path", "d:/coder") + "/" + coder.getPackageName().replaceAll("\\.", "/") + "/" + packageName.replaceAll("\\.", "/") + "/" + className;
			if (templateFileName != null && templateFileName.toLowerCase().endsWith(".ftl")) {
				Freemarker.printFreemarkerFile(templateFileName, outFileName, CodeGenerator.toMap(coder));
			}
			if (templateFileName != null && templateFileName.toLowerCase().endsWith(".vm")) {
				Freemarker.printVelocityFile(templateFileName, outFileName, CodeGenerator.toMap(coder));
			}
		}
	}

	/**
	 * 准备map对象
	 * 
	 * @param coder
	 * @return
	 */
	public static Map<String, Object> toMap(Coder coder) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coder", coder);
		map.put("fields", coder.getFields());
		return map;
	}

	private static Map<String, String> map;
	static {
		map = new HashMap<String, String>();
		map.put("varchar2", "String");
		map.put("varchar", "String");
		map.put("int", "Integer");
		map.put("datetime", "Date");
		map.put("date", "Date");
		map.put("timestamp", "Date");
		map.put("nvarchar", "String");
		map.put("char", "String");
		map.put("uniqueidentifier", "String");
		map.put("number", "BigDecimal");
		map.put("decimal", "BigDecimal");
		map.put("bigint", "Long");
		map.put("tinyint", "Boolean");
		map.put("blob", "Blob");
		map.put("clob", "String");
	}

	public static String getPropertyType(String dataType) {
		String tmp = dataType.toLowerCase();
		StringTokenizer st = new StringTokenizer(tmp);

		return (String) map.get(st.nextToken());
	}

	private static Map<String, String> modelMapping;
	static {
		modelMapping = new HashMap<String, String>();
		modelMapping.put("sys", "system");// 系统管理模块
		modelMapping.put("cfg", "config");// 配置管理模块
		modelMapping.put("ie", "index");// 指标管理模块
		modelMapping.put("prm", "relation");
		modelMapping.put("date", "Date");
	}

	public static String getModel(String model) {
		String model1 = modelMapping.get(model);
		if (model1 == null) {
			model1 = model;
		}
		return model1;
	}

	/***************************************************************************
	 * 将s_name 变为 sName;
	 * 
	 * @param sName
	 * @return
	 */
	public static String convertFieldName(String sName) {

		String[] str = sName.toLowerCase().split("_");

		StringBuffer beanS = new StringBuffer(str[0]);

		for (int i = 1; i < str.length; i++) {
			if ("".equals(str) || str == null) {
				continue;
			}
			String info = str[i];
			beanS.append(info.substring(0, 1).toUpperCase() + info.substring(1, info.length()));
		}
		return beanS.toString();
	}

	/**
	 * 处理部分关键字段
	 * 
	 * @param coder
	 */
	public static void processProperties(Coder coder) {
		CodeGenerator.processClassName(coder);
		CodeGenerator.processModelName(coder);
		CodeGenerator.processFunctionName(coder);
		CodeGenerator.processPackageName(coder);
		CodeGenerator.processFields(coder);
		CodeGenerator.processTitle(coder);
		if (coder.getCrtDate() == null) {
			coder.setCrtDate(new Date());
		}
	}

	/**
	 * 当类名为空时根据表名设置类名
	 * 
	 * @param className
	 * @return
	 */
	public static void processClassName(Coder coder) {
		String tableName = coder.getTableName();
		if (StringUtils.isEmpty(coder.getClassName())) {
			coder.setClassName(ReflectHelper.getPropertyName(tableName.substring(tableName.indexOf("_") + 1), false));
			coder.setClassDeclare(ReflectHelper.getPropertyName(tableName.substring(tableName.indexOf("_") + 1)));
		}
	}

	/**
	 * 当模块名为空时根据表名设置模块名
	 * 
	 * @param coder
	 */
	public static void processModelName(Coder coder) {
		String tableName = coder.getTableName();
		if (StringUtils.isEmpty(coder.getModelName())) {
			coder.setModelName(ReflectHelper.getPropertyName(getModel(tableName.substring(0, tableName.indexOf("_")).toLowerCase())));
		}
		String tableComment = coder.getTableComment();
		if (tableComment == null) {
			tableComment = tableName;
		}
		if (StringUtils.isEmpty(coder.getModelNameCn())) {
			String modelNameCn = tableComment;
			if (tableComment.indexOf("-") > 0) {
				modelNameCn = tableComment.substring(0, tableComment.indexOf("-"));
			}
			coder.setModelNameCn(modelNameCn);
		}
	}

	/**
	 * 当功能名为空时根据表名设置功能名
	 * 
	 * @param coder
	 */
	public static void processFunctionName(Coder coder) {
		String tableName = coder.getTableName();
		if (StringUtils.isEmpty(coder.getFunctionName())) {
			coder.setFunctionName(ReflectHelper.getPropertyName(tableName.substring(tableName.indexOf("_") + 1)));
		}
	}

	/**
	 * 当标题为空时根据表名设置标题
	 * 
	 * @param coder
	 */
	public static void processTitle(Coder coder) {
		if (StringUtils.isEmpty(coder.getTitle())) {
			coder.setTitle(coder.getModelNameCn() + "->" + coder.getTableComment());
		}
	}

	/**
	 * 当包名为空时根据表名设置包名
	 * 
	 * @param coder
	 */
	public static void processPackageName(Coder coder) {
		if (StringUtils.isEmpty(coder.getPackageName())) {
			coder.setPackageName(Configure.getProperty("coder.package.prefix", "com.afcac") + "." + coder.getModelName() + "." + coder.getFunctionName().toLowerCase());
		}
	}

	/**
	 * 处理字段名称首字母大写
	 * 
	 * @param coder
	 */
	public static void processFields(Coder coder) {
		for (Column column : coder.getFields()) {
			column.setPropFuncName(ReflectHelper.capitalName(column.getPropertyName()));
		}
	}
}
