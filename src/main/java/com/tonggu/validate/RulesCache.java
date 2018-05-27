/** 
 * @(#)RuleAnnotationCache.java 1.0.0 2014年12月23日 上午10:41:25  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tonggu.annotation.Rules;

/**
 * 注解规则缓存
 * <p>
 * 一般每个方法的注解都是固定不变的 所以可以缓存起来公用
 * </p>
 * 
 * @author 孟山
 * @version $Revision:1.0.0, $Date: 2014年12月23日 上午10:41:25 $
 */
public class RulesCache {

	private static Logger logger = LoggerFactory.getLogger(RulesCache.class);

	private static Map<String, Rules> annotationRules = new HashMap<String, Rules>();

	/**
	 * 获取注解规则
	 * <p>
	 * 如果缓存中存在则直接充缓存中取出，如缓存中无则直接根据反射去查找 并放到缓存中(无论查找结果是否为空)
	 * </p>
	 * 
	 * @param className
	 *            指定类的权限名
	 * @param methodName
	 *            指定类中的方法名
	 * @return
	 */
	public static Rules getAnnotationRules(String className, String methodName) {
		Rules rules = annotationRules.get(className + "." + methodName);
		if (rules == null) {
			rules = getAnnotationRules(className, methodName, Rules.class);

			annotationRules.put(className + "." + methodName, rules);
		}
		return rules;
	}

	/**
	 * 根据方法获取注解规则
	 * 
	 * @param method
	 * @return
	 */
	public static Rules getAnnotationRulesByMethod(Method method) {
		Rules rules = null;
		if (method != null) {
			rules = annotationRules.get(method.getClass().getName() + "." + method.getName());
			if (rules == null) {
				rules = method.getAnnotation(Rules.class);
				annotationRules.put(method.getClass().getName() + "." + method.getName(), rules);
			}
		}
		return rules;
	}

	/**
	 * 根据类的权限名和方法名查找当方法名的响应注解
	 * <p>
	 * <strong>如果类中有方法重载的话只取<i>第一个</i>请注意</strong>
	 * </p>
	 * 
	 * @param className
	 * @param methodName
	 * @param annotationClazz
	 * @return
	 */
	public static final <T extends Annotation> T getAnnotationRules(String className, String methodName, Class<T> annotationClazz) {
		T t1 = null;
		if (className != null && methodName == null) {
			Class<?> clazz = null;
			Method method = null;
			try {
				clazz = Class.forName(className, false, RulesCache.class.getClassLoader());
			} catch (ClassNotFoundException e) {
				logger.error("注解规则验证找不到相应的类:{}", className);
			}
			if (clazz != null) {
				Method[] methods = clazz.getMethods();
				for (Method methodTemp : methods) {
					if (methodTemp.getName().equals(methodName)) {
						method = methodTemp;
						break;
					}
				}
			}
			if (method != null) {
				t1 = method.getAnnotation(annotationClazz);
			}
		}
		return t1;
	}
}
