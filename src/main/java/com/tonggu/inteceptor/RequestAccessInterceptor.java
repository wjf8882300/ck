/** 
 * @(#)CustomerOperateInterceptor.java 1.0.0 2014年12月11日 上午10:15:54  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 
 * @author zhangyingxuan
 * @version $Revision:1.0.0, $Date: 2014年12月11日 上午10:15:54 $
 */
public class RequestAccessInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(RequestAccessInterceptor.class);

	/**
	 * 拦截匹配的URL，如果该请求不存在session则拒绝访问
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.debug("Entering...");
		HttpSession session = request.getSession(false);
		if (session == null) {
			log.error("Illegal request, session is null, client ip :" + request.getRemoteAddr());
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
