/** 
 * @(#)SLValidator.java 1.0.0 2014年12月20日 下午2:33:56  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.validator;

/**
 * 一般通常值验证器
 * 
 * @author 孟山
 * @version $Revision:1.0.0, $Date: 2014年12月20日 下午2:33:56 $
 */
public class SLValidatorValue extends SLValidatorAbstract {

	@Override
	public Object getValue(String value) {
		return value;
	}

	@Override
	protected String formatMessage(String message) {
		return message;
	}

}
