/** 
 * @(#)UserController.java 1.0.0 2016年5月16日 下午2:39:30  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.tonggu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tonggu.entity.UserEntity;
import com.tonggu.service.UserService;
import com.tonggu.vo.ResultVo;

/**   
 * 用户管理
 *  
 * @author  wangjf
 * @version $Revision:1.0.0, $Date: 2016年5月16日 下午2:39:30 $ 
 */
@Controller
@RequestMapping("/ck/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
	public String list(Model model) {
	
		return "/user/list";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = {"/detail"}, method = RequestMethod.GET)
	public String detail(Model model) {
	
		return "/user/detail";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = {"/grant"}, method = RequestMethod.GET)
	public String grant(Model model) {
	
		return "/user/grant";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = {"/queryAllUser"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo queryAllUser(Model model, @RequestBody Map<String, Object> params) {
		return userService.findAll(params);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = {"/saveUser"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo saveUser(Model model, @RequestBody Map<String, Object> params) {
		return userService.saveUser(params);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = {"/queryById"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo queryById(Model model, @RequestBody Map<String, Object> params) {
		UserEntity userEntity = userService.queryById((String)params.get("id"));
		if(userEntity == null) {
			return new ResultVo(false, "未查询到用户数据");
		}
		return new ResultVo(true, "查询成功", userEntity);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = {"/deleteUser"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo deleteUser(Model model, @RequestBody Map<String, Object> params) {
		return userService.deleteUser(params);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = {"/grantRole"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo grantRole(Model model, @RequestBody Map<String, Object> params) {
		return userService.grantRole(params);
	}
}
