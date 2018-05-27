/** 
 * @(#)RuleConstants.java 1.0.0 2015年1月9日 下午1:12:04  
 *  
 * Copyright © 2015 善林金融.  All rights reserved.  
 */

package com.tonggu.validate;

import java.util.regex.Pattern;

/**
 * 规则常量类
 * 
 * @author 孟山
 * @version $Revision:1.0.0, $Date: 2015年1月9日 下午1:12:04 $
 */
public class RuleConstants {
	/**
	 * 手机正则表达式
	 */
	public static final Pattern PATTERN_MOBILE = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
	/**
	 * 电话正则表达式
	 * <p>
	 * 首先验证不带区号<br />
	 * 再验证带区号的<br />
	 * 最后验证是否加分机的
	 * </p>
	 */
	public static final Pattern PATTERN_TELEPHONE = Pattern.compile("(^[1-9]{1}[0-9]{5,8}$)|(^[0][1-9]{2,3}-[0-9]{5,10}$)|(^[0][1-9]{2,3}-[0-9]{5,10}-[0-9]{1,6}$)");
	/**
	 * 邮箱正则表达式
	 */
	public static final Pattern PATTERN_EMAIL = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
	/**
	 * 身份证验证
	 * <p>
	 * 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
	 * </p>
	 */
	public static final Pattern PATTERN_IDENTIFICATION = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");

	/**
	 * <p>
	 * 银行卡或信用卡卡号验证
	 * </p>
	 * 根据这个表，我来总结一下：中国国内银行卡号，前六位为发卡行BIN号（IIN号，上图最后一列不要看错了），跟着四位可能为地区识别码（归属地区，
	 * 我无法确定所有银行卡号是否都是
	 * ），中间位个人账号标识，最后一位为验证码，总的长度<strong>从16到19都有</strong>（其实我对此数据的时效性有点怀疑
	 * ，因为它还是称之为BIN号
	 * ，现在应该称之为IIN号了）。除非有一张关于银行卡IIN号的基础数据表，无法很准确的判定银行卡号的位数，当然对于几个大的银行可能是比较好确定的。
	 * 
	 * 但是根据前台数据目前只验证8-22位
	 */
	public static final Pattern PATTERN_CREDIT_OR_BANKCARD = Pattern.compile("^[0-9]{8,22}$");

	/**
	 * 数字正则表达式
	 */
	public static final Pattern PATTERN_NUMBER = Pattern.compile("^\\d+(\\.\\d+)?$");

	/**
	 * 日期格式 yyyy-MM-dd
	 */
	public static final String DATE_FORMAT_yyyygMMgdd = "yyyy-MM-dd";
	/**
	 * 日期格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_yyyygMMgddkHHmmmmss = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期格式yyyy/MM/dd
	 */
	public static final String DATE_FORMAT_yyyyXMMXdd = "yyyy/MM/dd";
	
	/**
	 * 正整数正则表达式
	 */
	public static final Pattern PATTERN_POSITIVE_INEGER =  Pattern.compile("^[1-9]*[1-9][0-9]*$");
	
	/**
	 * 正数正则表达式(仅匹配123.22，小数位仅能出现两次)
	 */
	public static final Pattern PATTERN_POSITIVE_NUMBER = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
}
