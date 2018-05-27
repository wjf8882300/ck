/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tonggu.config;

import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new MyCustomizer();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*判断是否进行了实名认证�?�开户�?�设置交易密�?*/
		//registry.addInterceptor(businessInterceptor()).addPathPatterns("/account/recharge", "/account/withdraw","/account/cerRecharge");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/html/**")
	            .addResourceLocations("classpath:/static/html/");
	}

	@Bean
	public FilterRegistrationBean siteMeshFilter() {
		Filter filter = new SiteMeshFilter();
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(filter);
		registrationBean.setUrlPatterns(Arrays.asList("/*"));
		return registrationBean;
	}

	private static class MyCustomizer implements EmbeddedServletContainerCustomizer {

		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			container.addErrorPages(new ErrorPage(Throwable.class, "/html/error/error403.html"));
			container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/html/error/error403.html"));
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/html/error/error404.html"));
			container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/html/error/error405.html"));
			container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/html/error/error500.html"));
		}
	}

	 @Bean
	 public MappingJackson2HttpMessageConverter
	 customJackson2HttpMessageConverter() {
		 MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		 ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 jsonConverter.setObjectMapper(objectMapper);
		 return jsonConverter;
	 }
	
	 @Override
	 public void configureMessageConverters(List<HttpMessageConverter<?>>
	 converters) {
		 converters.add(customJackson2HttpMessageConverter());
		 super.configureMessageConverters(converters);
	 }

}