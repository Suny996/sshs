package com.sshs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.sshs.core.servlet.ViewDispatcherServlet;

@SpringBootApplication
public class SshsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SshsApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean<ViewDispatcherServlet> ServletRegistration() {
		ServletRegistrationBean<ViewDispatcherServlet> registration = new ServletRegistrationBean<ViewDispatcherServlet>(
				new ViewDispatcherServlet());
		registration.addUrlMappings("*.w");
		registration.addUrlMappings("*.dw");
		return registration;
	}
}
