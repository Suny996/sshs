package com.sshs.core.message;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.sshs.constant.Global;

/**
 * 
 * @author Suny
 *
 */
public class Message {
	String code;
	String msg;

	public Message(String code) {

		this.code = code;
		this.msg = Message.getMessage(code);
	}

	public Message(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public static String getMessage(String code) {
		Locale locale = null;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String local = request.getParameter("locale");
		if (StringUtils.isNotEmpty(local) && local.contains(Global.CHARACTER_UNDERLINE)) {
			locale = new Locale(local.split(Global.CHARACTER_UNDERLINE)[0], local.split(Global.CHARACTER_UNDERLINE)[1]);
		} else {
			locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		}
		if (locale == null) {
			locale = request.getLocale();
		}
		ResourceBundle resource = ResourceBundle.getBundle("i18n/messages", locale);
		try {
			String message = new String(resource.getString(code).getBytes("ISO-8859-1"), "UTF-8");
			return message;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return code;
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
