package com.tonggu.service;

import java.util.Map;

import com.tonggu.entity.MenuEntity;
import com.tonggu.exception.JJException;
import com.tonggu.vo.ResultVo;

public interface MenuService {

	/**
	 * 通过角色ID查询菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public ResultVo queryByRoleId(String roleId);
	
	/**
	 * 通过用户ID查询菜单
	 * 
	 * @param userId
	 * @return
	 */
	public ResultVo queryByUserId(String userId);
	
	/**
	 * 查询所有
	 * 
	 * @param params
	 * @return
	 * @throws JJException
	 */
	public ResultVo queryAll(Map<String, Object> params) throws JJException;
	
	/**
	 * 保存菜单
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo saveMenu(Map<String, Object> params) throws JJException;
	
	/**
	 * 更新菜单
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo updateMenu(Map<String, Object> params) throws JJException;
	
	/**
	 * 删除菜单
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo deleteMenu(Map<String, Object> params) throws JJException;
	
	/**
	 * 通过ID查询
	 *
	 * @author  wangjf
	 * @date    2016年5月16日 下午2:44:24
	 * @param id
	 * @return
	 */
	public MenuEntity queryById(String id);
}
