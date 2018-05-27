/** 
 * @(#)AlwaysSaveRequestCache.java 1.0.0 2014年11月28日 下午3:23:47  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

/**
 * 
 * 
 * @author zhangyingxuan
 * @version $Revision:1.0.0, $Date: 2014年11月28日 下午3:23:47 $
 */
public class AlwaysSaveRequestCache extends HttpSessionRequestCache {
	@Override
	public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
		final String SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";
		DefaultSavedRequest savedRequest = new DefaultSavedRequest(request, new PortResolverImpl());
		request.getSession().setAttribute(SAVED_REQUEST, savedRequest);
		logger.debug("DefaultSavedRequest added to Session: " + savedRequest);
	}
}
