/** 
 * @(#)ValidationRule.java 1.0.0 2014年12月20日 下午2:32:43  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * 字段验证规则
 * 
 * @author zhanghao
 * @version $Revision:1.0.0, $Date: 2014年12月20日 下午2:32:43 $
 */
public class RuleUtils {

	/**
	 * 判断不为空 且不为空串
	 * 
	 * @return
	 */
	public static boolean required(String str) {
		return !isBlank(str);
	}

	/**
	 * 判断不为空 且不为空串
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean required(Object obj) {
		return obj != null && required(obj.toString());
	}

	/**
	 * 校验是否为正确的手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		return mobile != null && RuleConstants.PATTERN_MOBILE.matcher(mobile).matches();
	}

	/**
	 * 校验是否是正确的手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(Object mobile) {
		return mobile != null && isMobile(mobile.toString());
	}

	/**
	 * 校验是否为指定格式的电话号码
	 * 
	 * @param tel
	 * @param pattern
	 * @return
	 */
	public static boolean isTel(String tel) {
		return tel != null && RuleConstants.PATTERN_TELEPHONE.matcher(tel).matches();
	}

	/**
	 * 校验是否为指定格式的电话号码
	 * 
	 * @param tel
	 * @param pattern
	 * @return
	 */
	public static boolean isTel(Object tel) {
		return tel != null && isTel(tel.toString());
	}

	/**
	 * 校验是否为正确邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		return email != null && RuleConstants.PATTERN_EMAIL.matcher(email).matches();
	}

	/**
	 * 校验是否为正确邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(Object email) {
		return email != null && isEmail(email.toString());
	}

	/**
	 * 校验是否是数字
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isNumber(String number) {
		return RuleConstants.PATTERN_NUMBER.matcher(number).matches();
	}

	/**
	 * 校验是否是数字
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isNumber(Object number) {
		return number != null && isNumber(number.toString());
	}

	/**
	 * 校验是否是整数
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean isDigist(String digist) {
		if (isEmpty(digist)) {
			return false;
		}
		for (int i = 0; i < digist.length(); i++) {
			if (!Character.isDigit(digist.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验是否是整数
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean isDigist(Object digist) {
		return digist != null && isDigist(digist.toString());
	}

	/**
	 * 校验是否是身份证
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isIdentification(String value) {
		return value != null && RuleConstants.PATTERN_IDENTIFICATION.matcher(value).matches();
	}

	/**
	 * 校验是否是身份证
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isIdentification(Object value) {
		return value != null && isIdentification(value.toString());
	}

	/**
	 * 校验是否相等
	 * 
	 * @param dest
	 * @param source
	 * @return
	 */
	public static boolean equalTo(String dest, String source) {
		return dest != null && source != null && dest.equals(source);
	}

	/**
	 * 校验是否相等
	 * 
	 * @param dest
	 * @param source
	 * @return
	 */
	public static boolean equalTo(Object dest, Object source) {
		return dest != null && source != null && equalTo(dest, source);
	}

	/**
	 * 校验最大长度是否是指定长度
	 * 
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static boolean maxLength(String str, int maxLength) {
		return str != null && str.length() < maxLength;
	}

	/**
	 * 校验最大长度是否是指定长度
	 * 
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static boolean maxLength(Object str, int maxLength) {
		return str != null && maxLength(str.toString(), maxLength);
	}

	/**
	 * 校验最小长度是否是指定长度
	 * 
	 * @param str
	 * @param minLength
	 * @return
	 */
	public static boolean minLength(String str, int minLength) {
		return str != null && str.length() > minLength;
	}

	/**
	 * 校验最小长度是否是指定长度
	 * 
	 * @param str
	 * @param minLength
	 * @return
	 */
	public static boolean minLength(Object str, int minLength) {
		return str != null && minLength(str.toString(), minLength);
	}

	/**
	 * 校验是否在指定数组中的数据
	 * 
	 * @param str
	 * @param args
	 * @return
	 */
	public static boolean inRange(String str, String[] args) {
		if (str == null || args.length == 0) {
			return false;
		}
		for (String arg : args) {
			if (str.equals(arg)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 校验是否在指定数组中的数据
	 * 
	 * @param str
	 * @param args
	 * @return
	 */
	public static boolean inRange(Object str, String[] args) {
		return str != null && inRange(str.toString(), args);
	}

	/**
	 * 校验是否在最大值内
	 * 
	 * @return
	 */
	public static boolean max(String number, double max) {
		return isNumber(number) && Double.valueOf(number) <= max;
	}

	/**
	 * 校验这是否在最大值内
	 * 
	 * @return
	 */
	public static boolean max(Object number, double max) {
		return number != null && max(number.toString(), max);
	}

	/**
	 * 校验是否在最小值内
	 * 
	 * @return
	 */
	public static boolean min(String number, double min) {
		return isNumber(number) && Double.valueOf(number) >= min;
	}

	/**
	 * 校验是否在最小值内
	 * 
	 * @return
	 */
	public static boolean min(Object number, double min) {
		return isNumber(number) && min(number.toString(), min);
	}

	/**
	 * 是否是银行卡号
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isBankCard(String cs) {
		return cs != null && RuleConstants.PATTERN_CREDIT_OR_BANKCARD.matcher(cs).matches();
	}

	/**
	 * 是否是银行卡号
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isBankCard(Object cs) {
		return cs != null && isBankCard(cs.toString());
	}

	/**
	 * 是否是某个整数的整数倍
	 * 
	 * @param number
	 *            待校验数据
	 * @param value
	 *            整数倍
	 * @return
	 */
	public static boolean multiple(Object number, int value) {
		return number != null && multiple(number.toString(), value);
	}

	/**
	 * 是否是某个整数的整数倍
	 * 
	 * @param number
	 *            待校验数据
	 * @param value
	 *            整数倍
	 * @return
	 */
	public static boolean multiple(String number, int value) {
		return isDigist(number) && Integer.valueOf(number) % value == 0;
	}

	/**
	 * 校验日期格式是否正确
	 * 
	 * @param date
	 *            日期字符串
	 * @param dateFormat
	 *            需要的格式
	 * @return
	 */
	public static boolean dateFormat(String date, String dateFormat) {
		boolean flag = false;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			if (simpleDateFormat.format(simpleDateFormat.parse(date)).equals(date))
				flag = true;
		} catch (ParseException e) {
		}
		return flag;
	}

	/**
	 * 校验日期格式是否正确
	 * 
	 * @param date
	 *            日期字符串
	 * @param dateFormat
	 *            需要的格式
	 * @return
	 */
	public static boolean dateFormat(Object date, String dateFormat) {
		return date != null && dateFormat(date.toString(), dateFormat);
	}

	/**
	 * 小数位数校验
	 * 
	 * @param number
	 *            待校验数字
	 * @param length
	 *            小数长度
	 * @return
	 */
	public static boolean decimals(Object number, int length) {
		return number != null && decimals(number.toString(), length);
	}

	/**
	 * 小数位数校验
	 * 
	 * @param number
	 *            待校验数字
	 * @param length
	 *            小数长度
	 * @return
	 */
	public static boolean decimals(String number, int length) {
		return length < 0 ? false : Pattern.compile("^\\d+(\\.\\d{0," + length + "})?$").matcher(number).matches();
	}

	/**
	 *
	 * <p>
	 * 判断不为空且不为空串
	 * </p>
	 * 
	 * <p>
	 * Checks if a CharSequence is empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 *
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer trims the
	 * CharSequence. That functionality is available in isBlank().
	 * </p>
	 *
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is empty or null
	 * @since 3.0 Changed signature from isEmpty(String) to
	 *        isEmpty(CharSequence)
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * <p>
	 * 判断字符串不为空且
	 * </p>
	 * <p>
	 * Checks if a CharSequence is whitespace, empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is null, empty or whitespace
	 * @since 2.0
	 * @since 3.0 Changed signature from isBlank(String) to
	 *        isBlank(CharSequence)
	 */
	public static boolean isBlank(String cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 校验是否是数字或字母
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean isLetterOrDigit(String digist) {
		if (isEmpty(digist)) {
			return false;
		}
		for (int i = 0; i < digist.length(); i++) {
			if (!Character.isLetterOrDigit(digist.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验是否是数字或字母
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean isLetterOrDigit(Object digist) {
		return digist != null && isLetterOrDigit(digist.toString());
	}
	
	/**
	 * 校验是否是字母
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean isLetter(String digist) {
		if (isEmpty(digist)) {
			return false;
		}
		for (int i = 0; i < digist.length(); i++) {
			if (!isLetter(digist.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验是否是字母
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean isLetter(Object digist) {
		return digist != null && isLetter(digist.toString());
	}
	
	/**
	 * 校验是否是数字或字母或特殊符号（@._）之一
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean isLetterOrDigitOrSymbol(String digist) {
		if (isEmpty(digist)) {
			return false;
		}
		for (int i = 0; i < digist.length(); i++) {
			if (!isLetter(digist.charAt(i))
					&& !isDigit(digist.charAt(i))
					&& digist.charAt(i) != '.' 
					&& digist.charAt(i) != '_'
					&& digist.charAt(i) != '@') {
				return false;
			}
		}
		return true;
	}
		
	
	/**
	 * 校验是否是字母（仅英文字符）
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean isLetter(char c) {
		return ((int)c>=65 && (int)c<=90) || ((int)c>=97 && (int)c<=122);
	}
	
	/**
	 * 校验是否数字
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean isDigit(char c) {
		return (int)c>=48 && (int)c<=57;
	}

	/**
	 * 校验是否是数字或字母或特殊符号（@._）之一
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean isLetterOrDigitOrSymbol(Object digist) {
		return digist != null && isLetterOrDigitOrSymbol(digist.toString());
	}
	
	/**
	 * 校验是否同时包含数字和字母
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean containsLetterAndDigit(String digist) {
		if (isEmpty(digist)) {
			return false;
		}
		boolean charResult = false;
		boolean digistResult = false;
		for (int i = 0; i < digist.length(); i++) {
			if (isLetter(digist.charAt(i))) {
				charResult = true;
				continue;
			}
			if (isDigit(digist.charAt(i))) {
				digistResult = true;
				continue;
			}
		}
		
		return charResult && digistResult;
	}

	/**
	 * 校验是否同时包含数字和字母
	 * 
	 * @param isDigist
	 * @return
	 */
	public static boolean containsLetterAndDigit(Object digist) {
		return digist != null && containsLetterAndDigit(digist.toString());
	}
	
	/**
	 * 校验是否是正整数
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isPositiveNumber(String number) {
		return RuleConstants.PATTERN_POSITIVE_INEGER.matcher(number).matches();
	}
	
	/**
	 * 校验是否是正实数(正整数+正浮点数，可用作金额)
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isArithmeticNumber(String number) {
		return RuleConstants.PATTERN_POSITIVE_NUMBER.matcher(number).matches();
	}
}
