package com.tonggu.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tonggu.entity.RoleEntity;
import com.tonggu.entity.RoleMenuEntity;
import com.tonggu.exception.JJException;
import com.tonggu.repository.RoleMenuRepository;
import com.tonggu.repository.RoleRepository;
import com.tonggu.repository.UserRoleRepository;
import com.tonggu.repository.custom.RoleRepositoryCustom;
import com.tonggu.service.RoleService;
import com.tonggu.util.BeanUtil;
import com.tonggu.vo.ResultVo;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleRepositoryCustom roleRepositoryCustom;
	
	@Autowired
	private RoleMenuRepository roleMenuRepository;
	
	@Override
	public ResultVo queryByUserId(String userId) {
		List<RoleEntity> roleList = userRoleRepository.findByUserId(userId);
		return new ResultVo(true, "查询角色成功", roleList);
	}

	@Override
	public ResultVo findAll(Map<String, Object> params) {
		Map<String, Object> result = Maps.newConcurrentMap();
		Page<Map<String, Object>> page = roleRepositoryCustom.queryAll(params);
		result.put("iTotalDisplayRecords", page.getTotalElements());
		result.put("data", page.getContent());
		return new ResultVo(true, "查询角色列表成功", result);
	}

	@Transactional(readOnly = false, rollbackFor = JJException.class)
	@Override
	public ResultVo saveRole(Map<String, Object> params) throws JJException {
		if(!StringUtils.isEmpty((String)params.get("id"))) {
			return updateRole(params);
		}
		RoleEntity roleEntity = BeanUtil.toBean(params, RoleEntity.class);
		roleRepository.save(roleEntity);
		return new ResultVo(true);
	}

	@Transactional(readOnly = false, rollbackFor = JJException.class)
	@Override
	public ResultVo updateRole(Map<String, Object> params) throws JJException {
		RoleEntity newRoleEntity = BeanUtil.toBean(params, RoleEntity.class);
		RoleEntity oldRoleEntity = roleRepository.findOne((String)params.get("id"));
		if(oldRoleEntity == null) {
			return new ResultVo(false, "角色不存在");
		}
		BeanUtil.copyProperties(oldRoleEntity, newRoleEntity);
		return new ResultVo(true, "更新成功");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, rollbackFor = JJException.class)
	@Override
	public ResultVo deleteRole(Map<String, Object> params) throws JJException {
		List<Map<String, Object>> delList = (List<Map<String, Object>>)params.get("delList");
		Set<String> set = Sets.newConcurrentHashSet();
		for(Map<String, Object> m : delList) {
			set.add((String)m.get("id"));
		}
		roleMenuRepository.batchDeleteByRoleId(set);
		userRoleRepository.batchDeleteByRoleId(set);
		roleRepository.batchDelete(set);
		return new ResultVo(true);
	}

	@Override
	public RoleEntity queryById(String id) {
		
		return roleRepository.findOne(id);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, rollbackFor = JJException.class)
	@Override
	public ResultVo grantRole(Map<String, Object> params) throws JJException {
		String roleId = (String)params.get("id");
		List<String> menuIds = (List<String>) params.get("menuIds");
		
		// 删除原先权限
		roleMenuRepository.deleteByRoleId(roleId);
		
		// 分配新的权限
		List<RoleMenuEntity> roleMenuList = Lists.newArrayList();
		for(String id : menuIds ) {
			RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
			roleMenuEntity.setMenuId(id);
			roleMenuEntity.setRoleId(roleId);
			roleMenuEntity.setBasicModelProperty((String)params.get("userId"), true);
			roleMenuList.add(roleMenuEntity);
		}
		roleMenuRepository.save(roleMenuList);
		return new ResultVo(true, "分配权限成功");
	}

}
