package com.sshs;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@ComponentScan(basePackages = { "com.sshs" })
@MapperScan("com.sshs.**.dao")
@SpringBootApplication
public class SshsApplication {
	// DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(SshsApplication.class, args);
	}

	/*@Bean
	public ServletRegistrationBean<ViewDispatcherServlet> ServletRegistration() {
		ServletRegistrationBean<ViewDispatcherServlet> registration = new ServletRegistrationBean<ViewDispatcherServlet>(
				new ViewDispatcherServlet());
		registration.addUrlMappings("*.w");
		registration.addUrlMappings("*.dw");
		return registration;
	}*/

	@Bean
	public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		/*
		 * //设置日期格式 ObjectMapper objectMapper = new ObjectMapper(); SimpleDateFormat smt
		 * = new SimpleDateFormat("yyyy-MM-dd"); objectMapper.setDateFormat(smt);
		 * mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper); //设置中文编码格式
		 * List<MediaType> list = new ArrayList<MediaType>();
		 * list.add(MediaType.APPLICATION_JSON_UTF8);
		 * mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
		 */
		return mappingJackson2HttpMessageConverter;
	}

	@Bean
	public StringHttpMessageConverter getStringHttpMessageConverter() {
		return new StringHttpMessageConverter();
	}

	@Bean
	public ByteArrayHttpMessageConverter getByteArrayHttpMessageConverter() {
		return new ByteArrayHttpMessageConverter();
	}
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage("com.sshs.*.*.dao");
        //配置通用mappers
        Properties properties = new Properties();
        properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        properties.setProperty("notEmpty", "false");
       // properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);

        return mapperScannerConfigurer;
    }

}
