/** 
 * @(#)LoginController.java 1.0.0 2016年5月18日 上午9:50:07  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.tonggu.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tonggu.security.CurrentUser;
import com.tonggu.security.DeEnCodeUtil;
import com.tonggu.security.WebUserDetails;
import com.tonggu.service.UserService;
import com.tonggu.vo.ResultVo;

/**   
 * 登录
 *  
 * @author  wangjf
 * @version $Revision:1.0.0, $Date: 2016年5月18日 上午9:50:07 $ 
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public String login(HttpServletRequest req, @CurrentUser WebUserDetails user, Model model) {
		if(user != null) {
			return "redirect:/index";
		} 
		
		Cookie[] cookies = req.getCookies();
		if(null != cookies && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if("7d418d57421e622343fe607c6b08204e".equals(cookies[i].getName())) {
					model.addAttribute("mobile", DeEnCodeUtil.decode(cookies[i].getValue()));
					break;
				}
			}
		}
		if(req.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null) {
			model.addAttribute("error", "true");
		}
		return "login";
	}
	
	@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
	@RequestMapping(value = {"/loginOut"}, method = RequestMethod.GET)
	public String home(Model model) {
		
		return "login";
	}
	
	@RequestMapping(value="/loginFailure", method = RequestMethod.GET)
	public @ResponseBody ResultVo loginFailure(HttpServletRequest req, @CurrentUser WebUserDetails user, Model model) {
		Exception ex = (Exception)req.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		return new ResultVo(false, ex.getMessage());
	}
}
