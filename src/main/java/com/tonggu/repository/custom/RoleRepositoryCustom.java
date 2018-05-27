package com.tonggu.repository.custom;

import java.util.Map;

import org.springframework.data.domain.Page;

public interface RoleRepositoryCustom {

	/**
	 * 分页查询所有角色
	 * 
	 * @param params
	 * @return
	 */
	public Page<Map<String, Object>> queryAll(Map<String, Object> params);
}
