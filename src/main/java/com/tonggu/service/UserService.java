/** 
 * @(#)UserService.java 1.0.0 2016年5月16日 下午2:42:40  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.tonggu.service;

import java.util.Map;

import com.tonggu.entity.UserEntity;
import com.tonggu.vo.ResultVo;

/**   
 * 用户接口
 *  
 * @author  wangjf
 * @version $Revision:1.0.0, $Date: 2016年5月16日 下午2:42:40 $ 
 */
public interface UserService {

	/**
	 * 通过ID查询
	 *
	 * @author  wangjf
	 * @date    2016年5月16日 下午2:44:24
	 * @param id
	 * @return
	 */
	public UserEntity queryById(String id);
	
	/**
	 * 登录
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo login(Map<String, Object> params);
	
	/**
	 * 通过登录名查询用户
	 * 
	 * @param loginName
	 * @return
	 */
	public UserEntity queryByLoginName(String loginName);
	
	/**
	 * 查询所有用户
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo findAll(Map<String, Object> params);
	
	/**
	 * 保存用户
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo saveUser(Map<String, Object> params);
	
	/**
	 * 更新用户
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo updateUser(Map<String, Object> params);
	
	/**
	 * 删除用户
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo deleteUser(Map<String, Object> params);
	
	/**
	 * 分配角色
	 * 
	 * @param params
	 * @return
	 */
	public ResultVo grantRole(Map<String, Object> params);
}
