package com.tonggu.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static Float stripToFloat(String str) {
		if(StringUtils.isEmpty(str)) {
			return 0.0f;
		}
		return Float.valueOf(str);
	}
	
	public static Integer stripToInteger(String str) {
		if(StringUtils.isEmpty(str)) {
			return 0;
		}
		return Integer.valueOf(str);
	}
	
	public static <T> List<T> arrayToList(T[] params) {
		if(params == null || params.length == 0) {
			return Arrays.asList();
		}
		return Arrays.asList(params);
	}
}
