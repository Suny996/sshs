package com.sshs.security.handle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import tk.mybatis.mapper.util.StringUtil;

/**
 * 登录失败后跳转处理
 * 
 * @author Suny
 *
 */
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Value("${login.defaultFailureUrl:}")
	private String defaultFailureUrl = null;
	boolean forwardToDestination = false;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if (StringUtil.isEmpty(defaultFailureUrl)) {
			logger.debug("没有设置defaultFailureUrl直接输出错误信息");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print("{\"code\":\"-000001\",\"message\":\"" + exception.getMessage() + "\"}");
			out.close();
		} else {
			saveException(request, exception);

			if (forwardToDestination) {
				logger.debug("Forwarding to " + defaultFailureUrl);

				request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
			} else {
				logger.debug("Redirecting to " + defaultFailureUrl);
				redirectStrategy.sendRedirect(request, response, defaultFailureUrl);
			}
		}
	}
}