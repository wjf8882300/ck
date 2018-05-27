package com.tonggu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.beanutils.Converter;

public class DataConverter implements Converter {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object convert(Class arg0, Object arg1) {
		// String p = (String) arg1;
		if (arg1 instanceof String) {
			String p = (String) arg1;
			if (p == null || p.trim().length() == 0) {
				return null;
			}
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df.parse(p.trim());
			} catch (Exception e) {
				try {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					return df.parse(p.trim());
				} catch (ParseException ex) {
					return null;
				}
			}
		}
		/**
		 * long 转换 Date java.util.Date
		 */
		else if (arg1 instanceof Long) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis((Long) arg1);
			return c.getTime();
		}
		return null;
	}
}
