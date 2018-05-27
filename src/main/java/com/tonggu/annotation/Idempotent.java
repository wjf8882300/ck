/** 
 * @(#)Idempotent.java 1.0.0 2015年10月19日 下午2:32:41  
 *  
 * Copyright © 2015 善林金融.  All rights reserved.  
 */ 

package com.tonggu.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**   
 * 
 *  
 * @author  wangjf
 * @version $Revision:1.0.0, $Date: 2015年10月19日 下午2:32:41 $ 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
    // marker annotation
}