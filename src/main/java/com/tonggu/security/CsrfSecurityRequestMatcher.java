/** 
 * @(#)CsrfRequestMatcher.java 1.0.0 2014年11月28日 下午2:19:28  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.security;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 
 * 
 * @author WangWei
 * @version $Revision:1.0.0, $Date: 2014年11月28日 下午2:19:28 $
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {

	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
	private List<String> execludeUrls;

	public List<String> getExecludeUrls() {
		return execludeUrls;
	}

	public void setExecludeUrls(List<String> execludeUrls) {
		this.execludeUrls = execludeUrls;
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		if (execludeUrls != null && execludeUrls.size() > 0) {
			String servletPath = request.getServletPath();
			for (String url : execludeUrls) {
				if (servletPath.contains(url)) {
					return false;
				}
			}
		}

		if (allowedMethods.matcher(request.getMethod()).matches()) {
			return false;
		}
		return true;
	}

}
