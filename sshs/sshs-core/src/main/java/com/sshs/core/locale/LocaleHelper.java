package com.sshs.core.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

public class LocaleHelper {
	public static Logger logger = Logger.getLogger(LocaleHelper.class);
	@Autowired
	private static HttpServletRequest request;

	public static void setLocale(HttpServletRequest request, String locale) {
		request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(locale));
	}

	public static String getMessage(String message) {
		RequestContext requestContext = new RequestContext(request);
		logger.debug(">>>>>>>" + message + ">>>>>>>" + requestContext.getMessage(message));
		return requestContext.getMessage(message);
	}

	public static String getLabel(String label) {
		//RequestContext requestContext = new RequestContext(request);
		//logger.debug(">>>>>>>" + label + ">>>>>>>" + requestContext.getMessage(label));
		return label;//requestContext.getMessage(label);
	}
}
