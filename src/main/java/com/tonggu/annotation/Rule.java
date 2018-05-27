/** 
 * @(#)Rule.java 1.0.0 2014年12月23日 上午9:20:29  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.annotation;

/**
 * 
 * 
 * @author 孟山
 * @version $Revision:1.0.0, $Date: 2014年12月23日 上午9:20:29 $
 */
public @interface Rule {
	/**
	 * 待检查字段或者值 根据不同的validator 获取方式不同
	 * 
	 * @return
	 */
	String name();

	/**
	 * 校验是否是必须的 默认是
	 * 
	 * @return
	 */
	boolean required() default false;

	/**
	 * 字段为空时的错误信息
	 * 
	 * @return
	 */
	String requiredMessage() default "字段必须填写";

	/**
	 * 校验是否是邮箱
	 * 
	 * @return
	 */
	boolean email() default false;

	/**
	 * 邮箱校验错误信息
	 * 
	 * @return
	 */
	String emailMessage() default "邮箱格式不正确";

	/**
	 * 校验手机号
	 * 
	 * @return
	 */
	boolean mobile() default false;

	/**
	 * 手机校验错误信息
	 * 
	 * @return
	 */
	String mobileMessage() default "手机格式不正确";

	/**
	 * 电话校验
	 * 
	 * @return
	 */
	boolean tel() default false;

	/**
	 * 电话校验错误信息
	 * 
	 * @return
	 */
	String telMessage() default "电话格式不正确";

	/**
	 * 数字校验
	 * 
	 * @return
	 */
	boolean number() default false;

	/**
	 * 数字校验错误信息
	 * 
	 * @return
	 */
	String numberMessage() default "数字格式不正确";

	/**
	 * 整数校验
	 * 
	 * @return
	 */
	boolean digist() default false;

	/**
	 * 整数校验错误信息
	 * 
	 * @return
	 */
	String digistMessage() default "整数格式不正确";

	/**
	 * 身份证校验
	 * 
	 * @return
	 */
	boolean identification() default false;

	/**
	 * 银行卡或信用卡卡号校验
	 * 
	 * @return
	 */
	boolean bankCard() default false;

	/**
	 * 银行卡或信用卡卡号校验失败信息
	 * 
	 * @return
	 */
	String bankCardMessage() default "卡号格式不正确";

	/**
	 * 身份证校验错误信息
	 * 
	 * @return
	 */
	String identificationMessage() default "身份证格式不正确";

	/**
	 * 相等校验
	 * 
	 * @return
	 */
	String equalTo() default "";

	/**
	 * 相等校验错误信息
	 * 
	 * @return
	 */
	String equalToMessage() default "必须相等不正确";

	/**
	 * 最大值校验
	 * 
	 * @return
	 */
	double max() default Double.MAX_VALUE;

	/**
	 * 最大值校验错误信息
	 * 
	 * @return
	 */
	String maxMessage() default "超过最大值";

	/**
	 * 最小值校验
	 * 
	 * @return
	 */
	double min() default Double.MIN_VALUE;

	/**
	 * 最小值校验错误信息
	 * 
	 * @return
	 */
	String minMessage() default "低于最小值";

	/**
	 * 最大长度校验
	 * 
	 * @return
	 */
	int maxLength() default Integer.MAX_VALUE;

	/**
	 * 最大长度校验错误信息
	 * 
	 * @return
	 */
	String maxLengthMessage() default "长度大于最大值";

	/**
	 * 最小长度校验
	 * 
	 * @return
	 */
	int minLength() default Integer.MIN_VALUE;

	/**
	 * 最小长度校验错误信息
	 * 
	 * @return
	 */
	String minLengthMessage() default "长度小于最小值";

	/**
	 * 是否区间存在校验
	 * 
	 * @return
	 */
	String[] inRange() default {};

	/**
	 * 区间存在校验错误信息
	 * 
	 * @return
	 */
	String inRangeMessage() default "不在区间内";

	/**
	 * 整数倍校验
	 * 
	 * @return
	 */
	int multiple() default 0;

	/**
	 * 整数倍校验错误信息
	 * 
	 * @return
	 */
	String multipleMessage() default "必须为整数倍";

	/**
	 * 日期格式校验
	 * 
	 * @return
	 */
	String dateFormat() default "";

	/**
	 * 日期格式校验错误信息
	 * 
	 * @return
	 */
	String dateFormatMessage() default "日期格式不正确";

	/**
	 * 小数位数校验
	 * 
	 * @return
	 */
	int decimals() default -1;

	/**
	 * 小数位数校验错误信息
	 * 
	 * @return
	 */
	String decimalsMessage() default "小数位数不符";

	/**
	 * 自定义正则校验
	 * 
	 * @return
	 */
	String regex() default "";

	/**
	 * 自定义正则校验错误信息
	 * 
	 * @return
	 */
	String regexMessage() default "";
	
	/**
	 * 校验必须为字母或者数字或特殊符号（@._）之一
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:11:16
	 * @return
	 */
	boolean letterOrDigitOrSymbol() default false;
	
	/**
	 * 校验必须为字母或者数字或特殊符号（@._）之一
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:11:31
	 * @return
	 */
	String letterOrDigitOrSymbolMessage() default "必须为字母或者数字或特殊符号（@._）之一";
	
	/**
	 * 校验是否同时包含数字和字母
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:16:14
	 * @return
	 */
	boolean containsLetterAndDigit() default false;
	
	/**
	 * 校验是否同时包含数字和字母
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:16:50
	 * @return
	 */
	String containsLetterAndDigitMessage() default "必须同时包含数字和字母";

	/**
	 * 不能为纯数字校验
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:19:52
	 * @return
	 */
	boolean pureDigit() default false;
	
	/**
	 * 不能为纯数字校验信息
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:20:53
	 * @return
	 */
	String pureDigitMessage() default "不能为纯数字";	
	
	/**
	 * 不能为纯字母校验
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:21:27
	 * @return
	 */
	boolean pureLetter() default false;
	
	/**
	 * 不能为纯字母校验信息
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:22:27
	 * @return
	 */
	String pureLetterMessage() default "不能为纯字母";
}
