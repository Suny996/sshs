package com.sshs;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * 启动类
 * 
 * @author Suny
 * @date 2018-01-20
 */
@ComponentScan(basePackages = { "com.sshs" })
@MapperScan("com.sshs.**.dao")
@SpringBootApplication
public class SshsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SshsApplication.class, args);
	}

	@Bean
	public static MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
		mapperScannerConfigurer.setBasePackage("com.sshs.*.*.dao");
		// 配置通用mappers
		Properties properties = new Properties();
		properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
		properties.setProperty("notEmpty", "false");
		mapperScannerConfigurer.setProperties(properties);

		return mapperScannerConfigurer;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
		c.setIgnoreUnresolvablePlaceholders(true);
		return c;
	}
}
