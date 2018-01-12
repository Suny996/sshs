package com.sshs.core.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Freemarker 模版引擎类 创建人：
 * 
 * @author Suny
 * @date 2017年11月5日
 * 
 * @version
 */
public class Freemarker {
	private static final Log logger = LogFactory.getLog(Freemarker.class);

	/**
	 * 打印到控制台(测试用)
	 * 
	 * @param templateFileName
	 */
	public static void print(String templateFileName, Map<String, Object> root, String encoding) throws Exception {
		try {
			// 通过Template可以将模板文件输出到相应的流
			Template temp = getFreemarkerTemplate(templateFileName, encoding);
			temp.process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过文件名加载模版
	 * 
	 * @param ftlName
	 */
	public static Template getFreemarkerTemplate(String templateFileName, String encoding) throws Exception {
		try {
			// 通过Freemaker的Configuration读取相应的ftl
			Configuration cfg = new Configuration(Configuration.getVersion());
			cfg.setEncoding(Locale.CHINA, encoding);
			// 设定去哪里读取相应的ftl模板文件
			cfg.setDirectoryForTemplateLoading(Configure.getClassPathFileDir(templateFileName));
			// 在模板文件目录中找到名称为name的文件
			Template temp = cfg.getTemplate(Configure.getClassPathFileShortName(templateFileName));
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将结果返回
	 * 
	 * @param template
	 *            模板内容
	 * @param paramMap
	 *            传入的map
	 * @return text 结果文本
	 */
	public static String printFreemarkerString(String templateFileName, Map<String, Object> paramMap, String encoding)
			throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = null;
		OutputStreamWriter out = null;
		String text = "";
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			out = new OutputStreamWriter(byteArrayOutputStream);
			Template template = getFreemarkerTemplate(templateFileName, encoding);
			// 模版输出
			template.process(paramMap, out);
			out.flush();
			out.close();
			text = byteArrayOutputStream.toString(encoding);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return text;
	}

	/**
	 * 输出到输出到文件
	 * 
	 * @param ftlName
	 *            ftl文件名
	 * @param root
	 *            传入的map
	 * @param outFile
	 *            输出后的文件全部路径
	 * @param filePath
	 *            输出前的文件上部路径
	 */
	public static void printFreemarkerFile(String templateFileName, String outFileName, Map<String, Object> paramMap,
			String encoding) throws Exception {
		try {
			File file = new File(outFileName);
			// 判断有没有父路径，就是判断文件整个路径是否存在
			if (!file.getParentFile().exists()) {
				// 不存在就全部创建
				file.getParentFile().mkdirs();
			}
			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
			Template template = getFreemarkerTemplate(templateFileName, "UTF-8");
			// 模版输出
			template.setOutputEncoding(encoding);
			template.process(paramMap, out);
			out.flush();
			out.close();
			logger.info("模板[" + templateFileName + "]生成文件[" + outFileName + "]，处理完成！");
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param template
	 * @param out
	 * @param map
	 */
	public static void printVelocityFile(String templateFileName, String out, Map<String, Object> map) {
		OutputStreamWriter writer = null;
		InputStreamReader reader = null;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(out, false), "UTF-8");

			reader = new InputStreamReader(Freemarker.class.getResourceAsStream(templateFileName), "UTF-8");

			Properties properties = new Properties();
			properties.put("input.encoding", "UTF-8");
			properties.put("output.encoding", "UTF-8");

			Velocity.init(properties);
			VelocityContext context = new VelocityContext(map);
			Velocity.evaluate(context, writer, "", reader);
			logger.info("模板[" + templateFileName + "]生成文件，处理完成！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
