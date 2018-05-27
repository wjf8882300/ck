package com.tonggu.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.tonggu.exception.JJException;

public class BeanUtil {
	public static <T> T toBean(Map<String, Object> map, Class<T> type) throws JJException {
		T obj;
		try {
			obj = type.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			BeanUtilsBean.getInstance().populate(obj, map);
		} catch (InstantiationException e) {
			throw new JJException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new JJException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new JJException(e.getMessage());
		}
		return obj;
	}
	
	public static void copyProperties(Object obj1, Object obj2) throws JJException {
		try {
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			BeanUtilsBean.getInstance().copyProperties(obj1, obj2);
		} catch (IllegalAccessException e) {
			throw new JJException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new JJException(e.getMessage());
		}
	}
}
