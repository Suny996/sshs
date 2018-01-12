package com.sshs.core.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

/**
 * 
 * @author Suny
 *
 */
public class LocaleHelper {
	public static Log logger = LogFactory.getLog(LocaleHelper.class);
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
		return label;
	}
}
