package com.sshs.core.view.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.sshs.constant.Global;
import com.sshs.core.locale.LabelResource;
import com.sshs.core.util.Configure;
import com.sshs.core.view.resolver.ViewResolver;

import net.sf.json.JSONObject;

/**
 * 视图解析处理 Servlet implementation class ViewResolverServlet
 * 
 * @author Suny
 * @date 2017-11-15
 */
public class ViewHelper {

	private static final Log logger = LogFactory.getLog(ViewHelper.class);

	/**
	 * 页面请求后缀pattren
	 */
	public static final String REQUEST_PATTREN_PAGE = ".w";

	/**
	 * 页面私有js请求后缀pattren
	 */
	public static final String REQUEST_PATTREN_JS = ".dw";

	/**
	 * html页面请求后缀
	 */
	private static final String REQUEST_PATTREN_HTML = ".html";
	/**
	 * jsp页面请求后缀
	 */
	private static final String REQUEST_PATTREN_JSP = ".jsp";

	/**
	 * jsp页面判断字符
	 */
	private static final String VIEW_CONTENT_KEYWORDS_JSP = "<%";

	/**
	 * 缓存视图文件目录前缀
	 */
	private static final String VIEW_CACHED_PATH_PREFIX = "/.cached";

	/**
	 * html视图模板文件默认路径
	 */
	@Value("${" + Configure.CONFIGURE_VIEW_TEMPLATE_HTML + "}")
	private static final String VIEW_TEMPLATE_PATH_HTML = "/templates/view/w3c-html5-template.html";

	/**
	 * 页面缓存 容器
	 */
	private static Map<String, String> CACHEDVIEW = new HashMap<String, String>(100);

	/**
	 * html view模板文件
	 */
	private static String viewHtmlTemplate = "<!DOCTYPE html><html lang=\"zh-CN\"><head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><base href=\"<!--_BasePath-->\">"
			+ "<!--_PageHeader--> </head> <!--_PageBody--> </html> <!--_PageFooter--> <!--_PageException-->";

	/**
	 * jsp view模板文件
	 */
	private static String viewJspTemplate = "";

	/**
	 * 运行模式
	 */
	@Value("${" + Configure.CONFIGURE_RUNMOD_FLAG + "}")
	public static String coreRunMode = Configure.CONFIGURE_RUNMOD_RUN;

	/**
	 * 缓存标志
	 */
	@Value("${" + Configure.CONFIGURE_CACHED_FLAG + "}")
	private static boolean viewCacheFlag = false;

	/**
	 * view文件后缀名
	 */
	private static String viewPattern = ".w.html";

	/**
	 * 
	 */
	private static String basePath = null;
	@Value("${debug.view.root}")
	private static String debugViewRoot = null;

	/**
	 * 
	 */
	static {
		ViewHelper.init();
	}

	/**
	 * 
	 * servlet初始化
	 * 
	 * 
	 */
	public static void init() {
		InputStream htmlTemplate = ViewHelper.class.getResourceAsStream(VIEW_TEMPLATE_PATH_HTML);
		if (htmlTemplate != null) {
			try {
				viewHtmlTemplate = IOUtils.toString(htmlTemplate, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (htmlTemplate != null) {
					try {
						htmlTemplate.close();
					} catch (IOException e) {
						e.printStackTrace();
						htmlTemplate = null;
					}
				}
			}
		}
	}

	public static Object doRequest(HttpServletRequest request) throws ServletException, IOException {
		// 从后台代码获取国际化信息
		String uri = request.getRequestURI();
		if (uri.endsWith(REQUEST_PATTREN_PAGE) || uri.endsWith(REQUEST_PATTREN_JS)) {
			/**
			 * 初始化basePath
			 */
			if (basePath == null) {
				basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath() + "/";
				viewHtmlTemplate = viewHtmlTemplate.replaceAll("\\<\\!--\\_BasePath\\--\\>", basePath);
			}
			String viewRequest = request.getRequestURI();

			Locale locale = null;
			String local = request.getParameter("locale");
			if (StringUtils.isNotEmpty(local) && local.contains(Global.CHARACTER_UNDERLINE)) {
				locale = new Locale(local.split(Global.CHARACTER_UNDERLINE)[0],
						local.split(Global.CHARACTER_UNDERLINE)[1]);
			} else {
				locale = (Locale) request.getSession()
						.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
			}
			if (locale == null) {
				locale = request.getLocale();
			}
			if (StringUtils.isNotEmpty(viewRequest)) {
				String contextPath = request.getContextPath();
				viewRequest = URLDecoder.decode(viewRequest.replaceFirst(contextPath, ""), "ISO-8859-1");
			}

			String cachedView = null;

			if (viewCacheFlag) {
				cachedView = CACHEDVIEW.get(viewRequest + locale);
			}
			if (StringUtils.isEmpty(cachedView)) {
				return doRequest(viewRequest, locale, request);
			} else {
				// request.getRequestDispatcher(cachedView).forward(request);
				return new ServletContextResource(request.getServletContext(), cachedView);
			}
		} else {
			return new ServletContextResource(request.getServletContext(), request.getServletPath());
		}
	}

	/**
	 * 处理页面请求
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static Object doRequest(String viewRequest, Locale locale, HttpServletRequest request)
			throws ServletException, IOException {
		// 创建缓存文件路径
		String filePath = Configure.getClasspath();

		String viewFileName = getViewNameNoPattern(viewRequest);
		String pattern = getViewNamePattern(viewRequest);
		InputStream view = null;
		// String debugViewRoot = Configure.getProperty("debug.view.root");
		if (StringUtils.isNotEmpty(debugViewRoot) && coreRunMode.equalsIgnoreCase(Configure.CONFIGURE_RUNMOD_DEBUG)) {
			view = FileUtils.openInputStream(new File(debugViewRoot + viewFileName + pattern));
		}
		// webroot下查找
		if (view == null) {
			view = request.getServletContext().getResourceAsStream(viewFileName + pattern);
		}
		// 类路径下查找classpath
		if (view == null) {
			view = request.getClass().getResourceAsStream(viewFileName + pattern);
		}
		String cachedView = null;
		if (view == null) {
			cachedView = "/error/404" + Global.CHARACTER_UNDERLINE + locale + ".html";
		} else {
			cachedView = VIEW_CACHED_PATH_PREFIX + viewFileName + Global.CHARACTER_UNDERLINE + locale + pattern;
			if ((pattern.endsWith(REQUEST_PATTREN_HTML) || pattern.endsWith(REQUEST_PATTREN_JSP))) {
				String text = doPageRequest(request, locale, view, viewFileName, pattern);
				if (text != null && text.contains(VIEW_CONTENT_KEYWORDS_JSP)) {
					pattern = REQUEST_PATTREN_JSP;
				}
				/*
				 * if (!viewCacheFlag && !REQUEST_PATTREN_JSP.equalsIgnoreCase(pattern)) {
				 * PrintWriter out = response.getWriter(); out.print(text); out.close(); return;
				 * } else {
				 */
				cachedView = VIEW_CACHED_PATH_PREFIX + viewFileName + Global.CHARACTER_UNDERLINE + locale + pattern;

				FileUtils.writeStringToFile(new File(filePath + cachedView), text, "UTF-8");
				CACHEDVIEW.put(viewRequest + locale, cachedView);
				if (view != null) {
					view.close();
				}
				logger.debug(">>>>>viewRequest:" + viewFileName + pattern + "  >>>>>view-text:" + text);
				// cachedView="system/coder/coder.w.html";
				// return new request.getRequestDispatcher(cachedView).forward(request);
				// }
			} else if (view != null) {
				cachedView = VIEW_CACHED_PATH_PREFIX + viewFileName + pattern;
				doResourceRequest(view, filePath + cachedView);
				CACHEDVIEW.put(viewRequest + locale, cachedView);
				if (view != null) {
					view.close();
				}
				// request.getRequestDispatcher(cachedView).forward(request);
			}
			if (view != null) {
				view.close();
			}
		}
		return new ClassPathResource(cachedView);
	}

	/**
	 * 处理页面请求
	 * 
	 * @param request
	 * @param input
	 * @param viewFileName
	 * @param pattern
	 * @param cachedName
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	protected static String doPageRequest(HttpServletRequest request, Locale locale, InputStream input,
			String viewFileName, String pattern) throws ServletException, IOException {
		if (input == null) {
			throw new ServletException("请求的视图文件:[" + (viewFileName + pattern) + "]不存在！");
		}
		ResourceBundle pubResource = ResourceBundle.getBundle("i18n/labels", locale);
		ResourceBundle privateResource = null;
		String privateResourceName = getViewDir(viewFileName) + "/i18n/label";
		try {
			privateResource = ResourceBundle.getBundle(privateResourceName, locale);
		} catch (MissingResourceException e) {
			if (privateResourceName.startsWith(Global.CHARACTER_SPRIT)) {
				privateResourceName = privateResourceName.substring(1);
			} else {
				privateResourceName = Global.CHARACTER_SPRIT + privateResourceName;
			}
			try {
				privateResource = ResourceBundle.getBundle(privateResourceName, locale);
			} catch (MissingResourceException e1) {
				logger.warn("label文件未找到！！！");
			}
		} catch (Exception e) {
			logger.warn("label文件未找到！！！");
		}
		LabelResource labelResource = new LabelResource(locale, pubResource, privateResource);
		labelResource.setPageId(request.getRequestURI().replaceAll("\\/", "").replaceAll("\\.", ""));
		// 处理视图内容
		String text = "";
		Map<String, String[]> parameters = request.getParameterMap();
		JSONObject params = formatParameters(parameters);
		String privateJs = "<script type=\"text/javascript\">var Model;\r\n try {require([\"scripts/sshs/js/document-ready.js\", \""
				+ request.getContextPath() + viewFileName + ".js\" ], function(comp,model) {\r\n"
				+ " if(comp){ comp.init(\"#" + labelResource.getPageId() + "\");}\r\n "
				+ "if(model) {model[\"params\"]=" + params
				+ ";\n  Model = model;\r\n if (typeof model.init === 'function') {\r\n" + "	model.init.apply(null);\r\n"
				+ "		} } }); }catch(e){}</script>";

		if (text != null && text.contains(VIEW_CONTENT_KEYWORDS_JSP)) {
			text = ViewResolver
					.resolve(input, labelResource, viewJspTemplate, request.getParameter("_pageType"), privateJs)
					.replaceAll("\\&lt;\\%", "\\<\\%").replaceAll("\\%\\&gt;", "\\%\\>");
		} else {
			text = ViewResolver.resolve(input, labelResource, viewHtmlTemplate, request.getParameter("_pageType"),
					privateJs);
		}
		return text;
	}

	/**
	 * 处理资源请求
	 * 
	 * @param request
	 * @param input
	 * @param viewFileName
	 * @param pattern
	 * @param cachedName
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	protected static void doResourceRequest(InputStream input, String fileName) throws ServletException, IOException {
		if (input != null) {
			OutputStream out = FileUtils.openOutputStream(new File(fileName));
			IOUtils.copy(input, out);
			out.close();
		}
	}

	/**
	 * 获取view名称不包含后缀名
	 * 
	 * @param viewName
	 * @return
	 */
	private static String getViewNameNoPattern(String viewName) {
		if (viewName.endsWith(REQUEST_PATTREN_PAGE)) {
			return viewName.substring(0, viewName.lastIndexOf(REQUEST_PATTREN_PAGE));
		} else if (viewName.endsWith(REQUEST_PATTREN_JS)) {
			viewName = viewName.substring(0, viewName.lastIndexOf(REQUEST_PATTREN_JS));
			if (viewName.contains(Global.CHARACTER_DOT)) {
				return viewName.substring(0, viewName.lastIndexOf(Global.CHARACTER_DOT));
			}
		}
		return viewName;
	}

	/**
	 * 获取view后缀名
	 * 
	 * @param viewName
	 * @return
	 */
	private static String getViewNamePattern(String viewName) {
		if (viewName.endsWith(REQUEST_PATTREN_PAGE)) {
			return viewPattern;
		} else if (viewName.endsWith(REQUEST_PATTREN_JS)) {
			viewName = viewName.substring(0, viewName.lastIndexOf(REQUEST_PATTREN_JS));
			if (viewName.contains(Global.CHARACTER_DOT)) {
				return viewName.substring(viewName.lastIndexOf(Global.CHARACTER_DOT));
			}
		}
		return "";
	}

	/**
	 * 获取view所在目录
	 * 
	 * @param viewName
	 * @return
	 */
	private static String getViewDir(String viewName) {
		if (viewName != null && viewName.contains(Global.CHARACTER_SPRIT)) {
			return viewName.substring(0, viewName.lastIndexOf(Global.CHARACTER_SPRIT));
		} else {
			return "";
		}
	}

	/**
	 * 格式化parameter参数
	 * 
	 * @param parameters
	 * @return
	 */
	private static JSONObject formatParameters(Map<String, String[]> parameters) {
		JSONObject params = new JSONObject();
		for (Entry<String, String[]> entry : parameters.entrySet()) {
			String[] value = entry.getValue();
			if (value.length == 0) {
				continue;
			}
			if (value.length == 1) {
				params.put(entry.getKey(), value[0]);
			} else {
				params.put(entry.getKey(), value);
			}
		}
		return params;
	}
}
