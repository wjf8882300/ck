/** 
 * @(#)SLMapValidator.java 1.0.0 2014年12月22日 下午3:59:48  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.validator;

import java.util.Map;

/**
 * 针对map的验证器
 * 
 * @author 孟山
 * @version $Revision:1.0.0, $Date: 2014年12月22日 下午3:59:48 $
 */
public abstract class SLValidatorMap extends SLValidatorAbstract {

	/**
	 * 参数map
	 */
	private Map<String, Object> parameters;

	public SLValidatorMap(Map<String, Object> parameters) {
		super();
		this.parameters = parameters;
	}

	/**
	 * 验证操作 核心业务接口
	 */
	protected abstract void validate();

	@Override
	public Object getValue(String value) {
		return parameters == null ? null : parameters.get(value);
	}

	@Override
	protected String formatMessage(String message) {
		return message;
	}
}
