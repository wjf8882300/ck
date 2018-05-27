package com.tonggu.service;

import java.util.Map;

import com.tonggu.entity.RoleEntity;
import com.tonggu.exception.JJException;
import com.tonggu.vo.ResultVo;

public interface RoleService {

	/**
	 * 通过用户ID查询角色
	 * 
	 * @param userId
	 * @return
	 */
	public ResultVo queryByUserId(String userId);
	
	/**
	 * 查询所有的角色
	 *  
	 * @param params
	 * @return
	 */
	public ResultVo findAll(Map<String, Object> params);
	
	/**
	 * 保存角色
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo saveRole(Map<String, Object> params) throws JJException;
	
	/**
	 * 更新角色
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo updateRole(Map<String, Object> params) throws JJException;
	
	/**
	 * 删除角色
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo deleteRole(Map<String, Object> params) throws JJException;
	
	/**
	 * 通过ID查询
	 *
	 * @author  wangjf
	 * @date    2016年5月16日 下午2:44:24
	 * @param id
	 * @return
	 */
	public RoleEntity queryById(String id);
	
	/**
	 * 分配权限
	 * 
	 * @param params
	 * @return
	 * @throws JJException
	 */
	public ResultVo grantRole(Map<String, Object> params) throws JJException;
}
