/** 
 * @(#)SecurityUtil.java 1.0.0 2014年10月9日 下午4:40:29  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.tonggu.entity.UserEntity;

/**
 * 
 * 便于在service中获取权限相关信息
 * 
 * @author zhangyx
 * @version $Revision:1.0.0, $Date: 2014年10月9日 下午4:40:29 $
 */
public class SecurityUtil {
	private SecurityUtil() {
	}

	/**
	 * 获取当前用户信息
	 * 
	 * @return
	 */
	public static UserEntity getCurrentUser() {
		try {
			Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (object instanceof WebUserDetails) {
				WebUserDetails webUserDetails = (WebUserDetails) object;
				return webUserDetails.getUser();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前用户的ID
	 * 
	 * @return
	 */
	public static String getCurrentUserId() {
		UserEntity currentUser = getCurrentUser();
		if (null != currentUser) {
			return (String) currentUser.getId();
		}
		return null;
	}
	
	/**
	 * 获取当前用户的信用等级
	 * 
	 * @return
	 */
	public static String getCurrentUserCreditLevel() {
		UserEntity currentUser = getCurrentUser();
		if (null != currentUser) {
			return (String) currentUser.getCredentialsCode();
		}
		return "0";
	}
	
	/**
	 * 获取CsrfToken
	 * 
	 * @return
	 */
	public static String findCsrfToken() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		CsrfToken loadToken = (CsrfToken) requestAttributes.getAttribute(CsrfToken.class.getName(), RequestAttributes.SCOPE_REQUEST);
		return loadToken.getToken();
	}
	
	/**
	 * 获取CsrfToken
	 * 
	 * @return
	 */
	public static String findCsrfHeadName() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		CsrfToken loadToken = (CsrfToken) requestAttributes.getAttribute(CsrfToken.class.getName(), RequestAttributes.SCOPE_REQUEST);
		return loadToken.getHeaderName();
	}

}
