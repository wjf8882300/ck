package com.tonggu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tonggu.entity.MenuEntity;
import com.tonggu.exception.JJException;
import com.tonggu.service.MenuService;
import com.tonggu.vo.ResultVo;

/**   
 * 菜单
 *  
 * @author  wangjf
 * @version $Revision:1.0.0, $Date: 2016年5月18日 上午9:50:07 $ 
 */
@Controller
@RequestMapping("/ck/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
	public String list(Model model) {
	
		return "/menu/list";
	}
	
	@RequestMapping(value = {"/detail"}, method = RequestMethod.GET)
	public String detail(Model model) {
	
		return "/menu/detail";
	}
	
	@RequestMapping(value = {"/queryByUserId"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo queryByUserId(Model model, @RequestBody Map<String, Object> params) {
		return menuService.queryByUserId("1");
	}
	
	@RequestMapping(value = {"/queryByRoleId"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo queryByRoleId(Model model, @RequestBody Map<String, Object> params) {
		return menuService.queryByRoleId((String)params.get("roleId"));
	}
	
	@RequestMapping(value = {"/saveMenu"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo saveMenu(Model model, @RequestBody Map<String, Object> params) throws JJException {
		return menuService.saveMenu(params);
	}
	
	@RequestMapping(value = {"/queryById"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo queryById(Model model, @RequestBody Map<String, Object> params) {
		MenuEntity menuEntity = menuService.queryById((String)params.get("id"));
		if(menuEntity == null) {
			return new ResultVo(false, "未查询到菜单数据");
		}
		return new ResultVo(true, "查询成功", menuEntity);
	}
	
	@RequestMapping(value = {"/deleteMenu"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo deleteMenu(Model model, @RequestBody Map<String, Object> params) throws JJException {
		return menuService.deleteMenu(params);
	}
	
	@RequestMapping(value = {"/queryAll"}, method = RequestMethod.POST)
	public @ResponseBody ResultVo queryAll(Model model, @RequestBody Map<String, Object> params) throws JJException {
		return menuService.queryAll(params);
	}
	
}	
