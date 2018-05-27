/** 
 * @(#)CustomerOperateInterceptor.java 1.0.0 2014年12月11日 上午10:15:54  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.inteceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tonggu.entity.UserEntity;
import com.tonggu.security.SecurityUrlProvider;
import com.tonggu.security.SecurityUtil;
import com.tonggu.service.UserService;
import com.tonggu.util.Constant;

/**
 * 
 * 
 * @author kongxiong
 * @version $Revision:1.0.0, $Date: 2014年12月11日 上午10:15:54 $
 */
public class CustomerOperateInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(CustomerOperateInterceptor.class);

	@Autowired
	private SecurityUrlProvider securityUrlProvider;
	
	@Autowired
	private UserService userService;

	/**
	 * 拦截冻结客户
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		UserEntity custInfo = SecurityUtil.getCurrentUser();

		if (!securityUrlProvider.isSecured(request.getRequestURI())) {
			return true;
		}
		if (custInfo != null) {
			if (probeCustStatus(custInfo.getId())) {
				String requestType = request.getHeader("X-Requested-With");
				HttpSession session = request.getSession(false);
				session.invalidate();
				if (requestType != null && requestType.equals("XMLHttpRequest")) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				} else {
					try {
						response.sendRedirect("/login");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return false;
			}
		}
		return true;
	}

	/** 检查用户状态 */
	private boolean probeCustStatus(String custId) {
		try {
			UserEntity custInfo = userService.queryById(custId);
			if (Constant.RECORD_STATUS_INVALID.equals(custInfo.getRecordStatus())) {
				log.info("用户已失效");
				return true;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
