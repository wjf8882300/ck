/** 
 * @(#)ClassUtil.java 1.0.0 2014年12月10日 下午8:08:25  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.security;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 
 * @author yuhuashan
 * @version $Revision:1.0.0, $Date: 2014年12月10日 下午8:08:25 $
 */
public final class SecurityUrlProvider {

	private static final Logger log = LoggerFactory.getLogger(SecurityUrlProvider.class);

	private List<String> securedMappings = new ArrayList<String>();

	public void init() throws Exception {
		log.debug("start init...");
		List<Class<?>> controllers = getController();
		for (Class<?> clazz : controllers) {
			List<String> actionMappings = getActionMapping(clazz);
			String[] controllerPaths = getControllerPath(clazz);
			for (String controller : controllerPaths) {
				for (String action : actionMappings) {
					StringBuffer mappings = new StringBuffer("/");
					mappings.append(controller);
					if(StringUtils.isNotBlank(action)){
						mappings.append("/").append(action);
					}
					String uri = mappings.toString().replaceAll("//", "/");
					securedMappings.add(uri);
				}
			}
		}
		log.debug("init done...");
	}

	public boolean isSecured(String path) {
		return securedMappings.contains(path);
	}

	private List<Class<?>> getController() throws Exception {
		List<Class<?>> controllers = new ArrayList<Class<?>>();
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
		for (BeanDefinition beanDefinition : scanner.findCandidateComponents("com.tonggu.controller")) {
			Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
			controllers.add(clazz);
		}
		return controllers;
	}

	private String[] getControllerPath(Class<?> clazz) {
		String[] path = new String[] { StringUtils.uncapitalize(clazz.getSimpleName()) };

		if (clazz.isAnnotationPresent(RequestMapping.class)) {
			RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
			String[] values = annotation.value();
			if (values != null && values.length > 0) {
				path = values;
			}
		}

		return path;
	}

	private List<String> getActionMapping(Class<?> clazz) {
		List<String> actions = new ArrayList<String>();
		Method[] methods = clazz.getDeclaredMethods();

		for (Method method : methods) {
			if (!method.isAnnotationPresent(Secured.class)) {
				continue;
			}
			if (method.isAnnotationPresent(RequestMapping.class)) {
				RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
				String[] values = requestMapping.value();
				if (values != null && values.length > 0) {
					actions.addAll(Arrays.asList(values));
				} else{
					actions.add("");
				}
			}
		}
		return actions;
	}

}
