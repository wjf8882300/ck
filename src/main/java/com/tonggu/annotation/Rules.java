/** 
 * @(#)Rules.java 1.0.0 2014年12月23日 上午9:14:26  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 规则注解入口
 * 
 * @author 孟山
 * @version $Revision:1.0.0, $Date: 2014年12月23日 上午9:14:26 $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Rules {

	/**
	 * 规则集合
	 * 
	 * @return
	 */
	Rule[] rules();

}
