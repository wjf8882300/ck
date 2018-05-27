/** 
 * @(#)SLValidator.java 1.0.0 2014年12月20日 下午2:33:56  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.validator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tonggu.validate.RuleUtils;


/**
 * 一般通常的验证器
 * 
 * @author 孟山
 * @version $Revision:1.0.0, $Date: 2014年12月20日 下午2:33:56 $
 */
public abstract class SLValidatorAbstract implements SLValidator {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 所有异常信息汇总
	 */
	private Set<String> messages = new HashSet<String>();
	/**
	 * 错误字段总数
	 */
	private int count = 0;

	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		for (Iterator<String> iterator = messages.iterator(); iterator.hasNext();) {
			sb.append(iterator.next());
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return getMessage();
	}

	public int getErrorsCount() {
		return count;
	}

	/**
	 * 检查是否有已有错误信息
	 * 
	 * @return
	 */
	public boolean hasErrors() {
		return count > 0;
	}

	/**
	 * 组装错误信息 目前只保留第一个出现错误的信息
	 * 
	 * @param flag
	 * @param message
	 */
	protected void populate(boolean flag, String message) {
		if (flag == false) {
			messages.add(formatMessage(message));
			count++;
		}
	}

	/**
	 * 格式化错误信息
	 * 
	 * @param message
	 * @return
	 */
	protected abstract String formatMessage(String message);

	/**
	 * 验证必填
	 * 
	 * @param value
	 *            验证值
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract required(String value, boolean check, String message) {
		if (check) {
			populate(RuleUtils.required(getValue(value)), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证是否为手机号
	 * 
	 * @param mobile
	 *            待验证值
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract mobile(String value, boolean check, String message) {
		if (check) {
			populate(RuleUtils.isMobile(getValue(value)), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证是否为电话
	 * 
	 * @param value
	 *            待验证值
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract tel(String value, boolean check, String message) {
		if (check) {
			populate(RuleUtils.isTel(getValue(value)), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证是否是邮箱格式
	 * 
	 * @param value
	 *            待验证值
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract email(String value, boolean check, String message) {
		if (check) {
			populate(RuleUtils.isEmail(getValue(value)), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证是否是银行卡
	 * 
	 * @param value
	 *            待验证值
	 * @param check
	 *            是否检查
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract bankCard(String value, boolean check, String message) {
		if (check) {
			populate(RuleUtils.isBankCard(getValue(value)), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证是否是数字
	 * 
	 * @param value
	 *            待验证值
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract number(String value, boolean check, String message) {
		if (check) {
			populate(RuleUtils.isNumber(getValue(value)), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证是否是整数
	 * 
	 * @param value
	 *            待验证值
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract digist(String value, boolean check, String message) {
		if (check) {
			populate(RuleUtils.isDigist(getValue(value)), formatMessage(message));
		}
		return this;
	}

	protected SLValidator identification(String value, boolean check, String message) {
		if (check) {
			populate(RuleUtils.isIdentification(getValue(value)), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证某两个值是否是相等
	 * 
	 * @param dest
	 * @param source
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract equalTo(String dest, String source, String message) {
		if (source != null && !"".equals(source)) {
			populate(RuleUtils.equalTo(getValue(dest), getValue(source)), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证长度必须大于最小值
	 * 
	 * @param value
	 *            待验证值
	 * @param minLength
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract minLength(String value, int minLength, String message) {
		if (minLength != Integer.MIN_VALUE) {
			populate(RuleUtils.minLength(getValue(value), minLength), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证长度必须小于最大值
	 * 
	 * @param value
	 *            待验证值
	 * @param maxLength
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract maxLength(String value, int maxLength, String message) {
		if (maxLength != Integer.MAX_VALUE) {
			populate(RuleUtils.maxLength(getValue(value), maxLength), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证必须大于最小值
	 * 
	 * @param value
	 *            待验证值
	 * @param min
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract min(String value, double min, String message) {
		if (min > Double.MIN_VALUE) {
			populate(RuleUtils.min(getValue(value), min), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证必须小于最大值
	 * 
	 * @param value
	 *            待验证值
	 * @param max
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract max(String value, double max, String message) {
		if (max < Double.MAX_VALUE) {
			populate(RuleUtils.max(getValue(value), max), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证值在数组范围内
	 * 
	 * @param value
	 *            待验证值
	 * @param array
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract inRange(String value, String[] array, String message) {
		if (array.length > 0) {
			populate(RuleUtils.inRange(getValue(value), array), formatMessage(message));
		}
		return this;
	}

	/**
	 * 验证倍数
	 * 
	 * @param value
	 *            待验证值
	 * @param mult
	 *            整数的倍数
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract multiple(String value, int mult, String message) {
		if (mult > 0) {
			populate(RuleUtils.multiple(getValue(value), mult), formatMessage(message));
		}
		return this;
	}

	/**
	 * 日期格式校验
	 * 
	 * @param value
	 *            待验证值
	 * @param format
	 *            日期格式 支持{@code SimpleDateFormat} 所支持的格式
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract dateFormat(String value, String format, String message) {
		if (format != null && !"".equals(format)) {
			populate(RuleUtils.dateFormat(getValue(value), format), formatMessage(message));
		}
		return this;
	}

	/**
	 * 小数位数校验
	 * 
	 * @param value
	 *            待验证值
	 * @param decimalsLength
	 *            允许最大小数位数
	 * @param message
	 *            验证失败时的错误信息
	 * @return
	 */
	protected SLValidatorAbstract decimals(String value, int decimalsLength, String message) {
		if (decimalsLength >= 0) {
			populate(RuleUtils.decimals(getValue(value), decimalsLength), formatMessage(message));
		}
		return this;
	}

	/**
	 * 指定正则表达式验证
	 * 
	 * @param value
	 * @param regex
	 * @param message
	 * @return
	 */
	protected SLValidatorAbstract regex(String value, String regex, String message) {
		if (regex != null && !RuleUtils.isBlank(regex)) {
			boolean match = false;
			try {
				match = Pattern.matches(regex, getValue(value).toString());
			} catch (Exception e) {
				logger.error("webservice规则注解正则表达式错误", e);
			}
			populate(match, formatMessage(message));
		}
		return this;
	}
	
	/**
	 * 验证是否为字母或者数字或特殊符号（@._）之一
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:24:43
	 * @param value
	 * @param check
	 * @param message
	 * @return
	 */
	protected SLValidatorAbstract letterOrDigitOrSymbol(String value, boolean check, String message) {
		if (check) {
			populate(RuleUtils.isLetterOrDigitOrSymbol(getValue(value)), formatMessage(message));
		}
		return this;
	}
	
	/**
	 * 验证是否同时包含数字和字母
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:14:45
	 * @param value
	 * @param check
	 * @param message
	 * @return
	 */
	protected SLValidatorAbstract containsLetterAndDigit(String value, boolean check, String message) {
		if (check) {
			populate(RuleUtils.containsLetterAndDigit(getValue(value)), formatMessage(message));
		}
		return this;
	}
	
	/**
	 * 验证是否非纯数字
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:25:20
	 * @param value
	 * @param check
	 * @param message
	 * @return
	 */
	protected SLValidatorAbstract pureDigit(String value, boolean check, String message) {
		if (check) {
			populate(!RuleUtils.isDigist(getValue(value)), formatMessage(message));
		}
		return this;
	}
	
	/**
	 * 验证是否非纯字母
	 *
	 * @author  wangjf
	 * @date    2015年7月6日 上午11:25:14
	 * @param value
	 * @param check
	 * @param message
	 * @return
	 */
	protected SLValidatorAbstract pureLetter(String value, boolean check, String message) {
		if (check) {
			populate(!RuleUtils.isLetter(getValue(value)), formatMessage(message));
		}
		return this;
	}
}
