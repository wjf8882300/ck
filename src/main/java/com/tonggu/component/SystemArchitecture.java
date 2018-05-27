/** 
 * @(#)SystemArchitecture.java 1.0.0 2014年11月23日 下午12:31:00  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.component;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 项目架构
 * 
 * @author zhangmingtao
 * @version $Revision:1.0.0, $Date: 2014年11月23日 下午12:31:00 $
 */
@Aspect
public class SystemArchitecture {

	/**
	 * A join point is in the web layer if the method is defined in a type in
	 * the com.tonggu.web package or any sub-package under that.
	 */
	@Pointcut("within(com.tonggu..controller..*)")
	public void inWebLayer() {
	}

	/**
	 * A join point is in the service layer if the method is defined in a type
	 * in the com.tonggu.thirdpp.service package or any sub-package under
	 * that.
	 */
	@Pointcut("within(com.tonggu..service..*)")
	public void inServiceLayer() {
	}

	/**
	 * A join point is in the data access layer if the method is defined in a
	 * type in the com.tonggu.thirdpp.dao package or any sub-package under
	 * that.
	 */
	@Pointcut("within(com.tonggu..dao..*)")
	public void inDataAccessLayer() {
	}

	/**
	 * A business service is the execution of any method defined on a service
	 * interface. This definition assumes that interfaces are placed in the
	 * "service" package, and that implementation types are in sub-packages.
	 *
	 * If you group service interfaces by functional area (for example, in
	 * packages com.xyz.someapp.abc.service and
	 * com.slfinacne.thirdpp.def.service) then the pointcut expression
	 * "execution(* com.tonggu.thirdpp..service..(..))" could be used
	 * instead.
	 *
	 * Alternatively, you can write the expression using the bean PCD, like so
	 * "bean(Service)". (This assumes that you have named your Spring service
	 * beans in a consistent fashion.)
	 */
	@Pointcut("execution(* com.tonggu..service..*(..))")
	public void businessService() {
	}

	/**
	 * A data access operation is the execution of any method defined on a dao
	 * interface. This definition assumes that interfaces are placed in the
	 * "dao" package, and that implementation types are in sub-packages.
	 */
	@Pointcut("execution(* com.tonggu..dao..*(..))")
	public void dataAccessOperation() {
	}
}
