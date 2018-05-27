/** 
 * @(#)LoginSuccessHandler.java 1.0.0 2014-10-8 上午11:07:42  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.tonggu.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.tonggu.service.UserService;

/**
 * 
 * 客户登录成功后，把当前登录信息保存到session中，重新封装便于获取
 * 
 * @author zhangyx
 * @version $Revision:1.0.0, $Date: 2014-10-8 上午11:07:42 $
 */
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	public static final Logger logger = LoggerFactory.getLogger(LogoutSuccessHandler.class);

	@Autowired
	private UserService userService;

	private boolean invalidateHttpSession = true;
	
//	@Value("${slb.sessionCookie.domain}")
//	private String cookieDomain;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Cookie nickNameCookie = new Cookie("nickName", null);
		nickNameCookie.setPath("/");
		nickNameCookie.setMaxAge(0);
		//nickNameCookie.setDomain(cookieDomain);
		response.addCookie(nickNameCookie);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			//map.put("loginUser", SessionUtil.getCurrentUserId(request));
		//	map.put("loginIp", SessionUtil.getIpAddr(request));
			map.put("logOutDate", new Date());
			//custInfoService.recordLoginLog(map);
		} catch (Exception e) {
			//logger.error("记录登出日志出错，客户ID：" + SessionUtil.getCurrentUserId(request) + " , 登出IP： "
			//		+ SessionUtil.getIpAddr(request) + " ， 详细信息如下：" + e);
		}

		if (invalidateHttpSession) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				logger.debug("Invalidating session: " + session.getId());
				session.invalidate();
			}
		}

		super.handle(request, response, authentication);
	}

	public boolean isInvalidateHttpSession() {
		return invalidateHttpSession;
	}

	public void setInvalidateHttpSession(boolean invalidateHttpSession) {
		this.invalidateHttpSession = invalidateHttpSession;
	}

}
