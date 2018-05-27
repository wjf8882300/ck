/** 
 * @(#)SLValidator.java 1.0.0 2014年12月20日 下午2:33:56  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.validator;

/**
 * 参数输入验证器
 * 
 * @author zhanghao
 * @version $Revision:1.0.0, $Date: 2014年12月20日 下午2:33:56 $
 */
public interface SLValidator {

	/**
	 * 获取错误信息
	 * 
	 * @return
	 */
	public String getMessage();

	/**
	 * 是否有错误信息
	 * 
	 * @return
	 */
	public boolean hasErrors();

	/**
	 * 对value值进行一定的处理
	 * 
	 * @return
	 */
	public Object getValue(String value);
}
