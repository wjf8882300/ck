/** 
 * @(#)IndexController.java 1.0.0 2016年5月18日 上午9:55:04  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.tonggu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tonggu.security.CurrentUser;
import com.tonggu.security.WebUserDetails;
import com.tonggu.service.MenuService;
import com.tonggu.vo.ResultVo;

/**   
 * 首页
 *  
 * @author  wangjf
 * @version $Revision:1.0.0, $Date: 2016年5月18日 上午9:55:04 $ 
 */
@Controller
public class IndexController {
	
	@Autowired
	private MenuService menuService;

	/**
	 * 跳转到首页
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = {"/index"}, method = RequestMethod.GET)
	public String index(@CurrentUser WebUserDetails user, Model model) {
		//加载菜单
		ResultVo resultVo = menuService.queryByUserId(user.getUser().getId());
		model.addAttribute("menuList", resultVo.getValue("data"));
		return "index";
	}
}
