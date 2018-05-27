/** 
 * @(#)UserServiceImpl.java 1.0.0 2016年5月16日 下午2:45:27  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.tonggu.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.hash.Hashing;
import com.tonggu.entity.UserEntity;
import com.tonggu.entity.UserRoleEntity;
import com.tonggu.repository.UserRepository;
import com.tonggu.repository.UserRoleRepository;
import com.tonggu.repository.custom.UserRepositoryCustom;
import com.tonggu.service.UserService;
import com.tonggu.vo.ResultVo;

/**   
 * 用户接口实现类
 *  
 * @author  wangjf
 * @version $Revision:1.0.0, $Date: 2016年5月16日 下午2:45:27 $ 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRepositoryCustom userRepositoryCustom;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public UserEntity queryById(String id) {
	
		return userRepository.findOne(id);
	}

	@Override
	public ResultVo login(Map<String, Object> params) {
		String loginName = (String)params.get("loginName");
		String loginPassword = (String)params.get("loginPassword");
		UserEntity userEntity =userRepository.findByLoginNameAndLoginPassword(loginName, loginPassword);
		if(userEntity == null) {
			return new ResultVo(false, "用户名或密码错误");
		}
		return new ResultVo(true);
	} 
	
	@Override
	public UserEntity queryByLoginName(String loginName){
		return userRepository.findByLoginName(loginName);
	}

	@Override
	public ResultVo findAll(Map<String, Object> params) {
		Map<String, Object> result = Maps.newConcurrentMap();
		Page<Map<String, Object>> page = userRepositoryCustom.queryAll(params);
		result.put("iTotalDisplayRecords", page.getTotalElements());
		result.put("data", page.getContent());
		return new ResultVo(true, "查询用户列表成功", result);
	}

	@Transactional(readOnly = false)
	@Override
	public ResultVo saveUser(Map<String, Object> params) {
		if(!StringUtils.isEmpty((String)params.get("id"))) {
			return updateUser(params);
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setLoginName((String)params.get("loginName"));
		userEntity.setLoginPassword(Hashing.md5().hashString((String)params.get("loginPassword"), Charsets.UTF_8).toString());
		userEntity.setCustName((String)params.get("custName"));
		userEntity.setCredentialsCode((String)params.get("credentialsCode"));
		userEntity.setEmail((String)params.get("email"));
		userEntity.setMobile((String)params.get("mobile"));
		userEntity.setBasicModelProperty((String)params.get("userId"), true);
		userRepository.save(userEntity);
		return new ResultVo(true);
	}

	@Transactional(readOnly = false)
	@Override
	public ResultVo updateUser(Map<String, Object> params) {
		UserEntity userEntity = userRepository.findOne((String)params.get("id"));
		if(userEntity == null) {
			return new ResultVo(false, "该用户不存在");
		}
		userEntity.setLoginName((String)params.get("loginName"));
		userEntity.setLoginPassword(Hashing.md5().hashString((String)params.get("loginPassword"), Charsets.UTF_8).toString());
		userEntity.setCustName((String)params.get("custName"));
		userEntity.setCredentialsCode((String)params.get("credentialsCode"));
		userEntity.setEmail((String)params.get("email"));
		userEntity.setMobile((String)params.get("mobile"));
		userEntity.setBasicModelProperty((String)params.get("userId"), false);
		userRepository.save(userEntity);
		return new ResultVo(true);
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = false)
	@Override
	public ResultVo deleteUser(Map<String, Object> params) {
		List<Map<String, Object>> delList = (List<Map<String, Object>>)params.get("delList");
		Set<String> set = Sets.newConcurrentHashSet();
		for(Map<String, Object> m : delList) {
			set.add((String)m.get("id"));
		}
		userRoleRepository.batchDeleteByUserId(set);
		userRepository.batchDelete(set);
		return new ResultVo(true);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	@Override
	public ResultVo grantRole(Map<String, Object> params) {
		String userId = (String)params.get("id");
		List<String> roleIds = (List<String>) params.get("roleIds");
		
		// 删除原先角色
		userRoleRepository.batchDeleteByUserId(Sets.newHashSet(userId));
		
		// 分配新的角色
		List<UserRoleEntity> userRoleList = Lists.newArrayList();
		for(String id : roleIds ) {
			UserRoleEntity userRoleEntity = new UserRoleEntity();
			userRoleEntity.setUserId(userId);
			userRoleEntity.setRoleId(id);
			userRoleEntity.setBasicModelProperty((String)params.get("userId"), true);
			userRoleList.add(userRoleEntity);
		}
		userRoleRepository.save(userRoleList);
		return new ResultVo(true, "分配角色成功");
	}
}
