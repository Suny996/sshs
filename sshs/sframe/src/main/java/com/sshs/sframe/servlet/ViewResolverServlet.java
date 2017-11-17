package com.sshs.sframe.servlet;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sshs.sframe.util.Configure;
import com.sshs.sframe.view.resolver.ViewResolver;

/**
 * @see 视图解析处理 Servlet implementation class ViewResolverServlet
 * 
 * @author Suny
 * @date 2017-11-15
 */
@WebServlet(name = "ViewResolverServlet", urlPatterns = { "*.d" }, loadOnStartup = 2)
public class ViewResolverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ViewResolverServlet.class);
	/**
	 * 页面缓存 容器
	 */
	private static Map<String, String> _cachedView = new HashMap<String, String>();

	/**
	 * html view模板文件
	 */
	private static String viewHtmlTemplate = "<!DOCTYPE html><html lang=\"zh-CN\"><head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><base href=\"${_BasePath}\">"
			+ "${_PageHeader} </head> ${_PageBody} </html> ${_PageFooter} ${_PageException}";

	/**
	 * jsp view模板文件
	 */
	private static String viewJspTemplate = "";

	/**
	 * 缓存标志
	 */
	private static boolean viewCacheFlag = true;

	/**
	 * view文件后缀名
	 */
	private static String viewPattern = ".view.xml";

	/**
	 * 
	 */
	private static String basePath = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewResolverServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		if ("false".equalsIgnoreCase(Configure.getProperty("view.cached"))) {
			viewCacheFlag = false;
		} else if (!"run".equalsIgnoreCase(Configure.getProperty("runMode"))
				&& !Configure.getProperty("runMode", "prod").toUpperCase().contains("PROD")) {
			viewCacheFlag = false;
		}
		InputStream htmlTemplate = config.getServletContext().getResourceAsStream(
				Configure.getProperty("view.html.template.path", "/META-INF/w3c-html5-template.html"));
		if (htmlTemplate == null) {
			htmlTemplate = this.getClass().getResourceAsStream(
					Configure.getProperty("view.html.template.path", "/META-INF/w3c-html5-template.html"));
		}
		if (htmlTemplate != null) {
			try {
				viewHtmlTemplate = IOUtils.toString(htmlTemplate, "UTF-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (htmlTemplate != null) {
					try {
						htmlTemplate.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						htmlTemplate = null;
					}
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 初始化basePath
		 */
		if (basePath == null) {
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/";
			viewHtmlTemplate = viewHtmlTemplate.replaceAll("\\$\\{\\_BasePath\\}", basePath);
		}

		// TODO Auto-generated method stub
		String viewRequest = request.getRequestURI();
		if (StringUtils.isNotEmpty(viewRequest)) {
			viewRequest = viewRequest.replaceFirst(request.getContextPath(), "");
		}
		String cachedView = null;
		String cachedJs = null;
		if (viewCacheFlag) {
			cachedView = _cachedView.get(viewRequest);
		}
		if (StringUtils.isEmpty(cachedView)) {
			String viewFileName = getViewNameNoPattern(viewRequest);
			InputStream view = request.getServletContext().getResourceAsStream(viewFileName + viewPattern);
			if (view == null) {
				view = request.getClass().getResourceAsStream(viewFileName + viewPattern);
			}
			if (view == null) {
				throw new ServletException("请求的视图文件:[" + (viewFileName + viewPattern) + "]不存在！");
			}
			Document doc = Jsoup.parse(view, "UTF-8", "");
			// 处理视图内容
			String text = "";
			cachedJs = cachedJs(request, viewFileName);

			if (text != null && text.contains("<%")) {
				cachedView = "/.cached" + viewFileName + ".jsp";
				text = ViewResolver.resolve(doc, viewJspTemplate, cachedJs).replaceAll("\\&lt;\\%", "\\<\\%")
						.replaceAll("\\%\\&gt;", "\\%\\>");
			} else {
				cachedView = "/.cached" + viewFileName + ".html";
				text = ViewResolver.resolve(doc, viewHtmlTemplate, cachedJs);
			}

			logger.debug(">>>>>viewRequest:" + viewRequest + "  >>>>>view-text:" + text);
			if (viewCacheFlag || cachedView.endsWith(".jsp")) {
				String filePath = request.getServletContext().getRealPath("");

				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(filePath + "/" + cachedView), "UTF-8")));
				// 在文件中写入数据
				out.write(text);
				// 关闭文件流
				out.close();
				_cachedView.put(viewRequest, cachedView);
				request.getRequestDispatcher(cachedView).forward(request, response);
			} else {
				PrintWriter out = response.getWriter();
				out.print(text);
			}
			if (view != null) {
				view.close();
			}
		} else {
			request.getRequestDispatcher(cachedView).forward(request, response);
		}
	}

	/**
	 * 获取view名称不包含后缀名
	 * 
	 * @param viewName
	 * @return
	 */
	private String getViewNameNoPattern(String viewName) {
		return viewName.substring(0, viewName.lastIndexOf("."));
	}

	/**
	 * 缓存js文件
	 * 
	 * @param request
	 * @param viewJsName
	 * @return
	 */
	private String cachedJs(HttpServletRequest request, String viewJsName) {
		viewJsName += ".js";
		InputStream js = request.getServletContext().getResourceAsStream(viewJsName);
		if (js == null) {
			js = request.getClass().getResourceAsStream(viewJsName);
		}
		if (js == null) {
			return "";
		} else {
			viewJsName = ".cached" + viewJsName;
			String filePath = request.getServletContext().getRealPath("");
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(filePath + "/" + viewJsName), "UTF-8")));
				// 在文件中写入数据
				out.write(IOUtils.toString(js, "UTF-8"));
				// 关闭文件流
				out.close();
				return "<script type=\"text/javascript\" src=\"" + viewJsName + "\"></script>";
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					js.close();
				} catch (IOException e) {
					js = null;
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "";
	}
}
