/** 
 * @(#)Md5PasswordEncoder.java 1.0.0 2014年10月15日 上午10:57:50  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.xml.XmlEscapers;


/**
 * 
 * 
 * @author zhangyingxuan
 * @version $Revision:1.0.0, $Date: 2014年10月15日 上午10:57:50 $
 */
public final class WebPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return Hashing.md5().hashString(rawPassword, Charsets.UTF_8).toString();
	}

	/**
	 * rawPassword 用户从界面上输入的密码 encodedPassword 从存储中取出的密码
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		CustomizeDes des = new CustomizeDes();
		CsrfToken loadToken = (CsrfToken) requestAttributes.getAttribute(CsrfToken.class.getName(), RequestAttributes.SCOPE_REQUEST);
		return Hashing.md5().hashString(XmlEscapers.xmlContentEscaper().escape(des.strDec(rawPassword.toString(), loadToken.getToken(), null, null)), Charsets.UTF_8).toString().equals(encodedPassword);
	}

}
