package com.tonggu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tonggu.entity.RoleEntity;
import com.tonggu.exception.JJException;
import com.tonggu.service.RoleService;
import com.tonggu.vo.ResultVo;

/**   
 * 角色管理
 *  
 * @author  wangjf
 * @version $Revision:1.0.0, $Date: 2016年5月18日 上午9:50:07 $ 
 */
@Controller
@RequestMapping("/ck/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
	public String list(Model model) {
	
		return "/role/list";
	}
	
	@RequestMapping(value = {"/detail"}, method = RequestMethod.GET)
	public String detail(Model model) {
	
		return "/role/detail";
	}
	
	@RequestMapping(value = {"/grant"}, method = RequestMethod.GET)
	public String grant(Model model) {
	
		return "/role/grant";
	}
	
	/**
	 * 查询所的角色
	 * @param params
	 * @return ResultVo
	 */
	@RequestMapping(value="/queryAll", method = RequestMethod.POST)
	public @ResponseBody ResultVo queryAll(Model model, @RequestBody Map<String, Object> params){
		ResultVo vo= roleService.findAll(params);
		System.out.println("vo ="+vo.getValue("roleName"));
		return vo;
	}
	
	
	@RequestMapping(value = {"/saveRole"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo saveRole(Model model, @RequestBody Map<String, Object> params) throws JJException {
		return roleService.saveRole(params);
	}
	
	@RequestMapping(value = {"/queryById"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo queryById(Model model, @RequestBody Map<String, Object> params) {
		RoleEntity RoleEntity = roleService.queryById((String)params.get("id"));
		if(RoleEntity == null) {
			return new ResultVo(false, "未查询到角色数据");
		}
		return new ResultVo(true, "查询成功", RoleEntity);
	}
	
	@RequestMapping(value = {"/deleteRole"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo deleteRole(Model model, @RequestBody Map<String, Object> params) throws JJException {
		return roleService.deleteRole(params);
	}
	
	@RequestMapping(value = {"/grantRole"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo grantRole(Model model, @RequestBody Map<String, Object> params) throws JJException {
		return roleService.grantRole(params);
	}
	
	@RequestMapping(value = {"/queryByUserId"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo queryByUserId(Model model, @RequestBody Map<String, Object> params) throws JJException {
		return roleService.queryByUserId((String)params.get("userId"));
	}
}
