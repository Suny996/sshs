package com.sshs.security.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 登录成功后跳转处理及功能菜单数据组装类
 * @author Suny
 *
 */
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Value("${login.mainPage:}")
	public String mainPage;

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}
		request.getRequestDispatcher(targetUrl).forward(request, response);
	}

	/**
	 * 可自定义登录成功后跳转到的首页
	 * @param authentication
	 * @return
	 */
	protected String determineTargetUrl(Authentication authentication) {
		String url = mainPage;
		return url;
	}
}