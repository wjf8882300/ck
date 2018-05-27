package com.tonggu.repository.custom;

import java.util.Map;

import org.springframework.data.domain.Page;

public interface MenuRepositoryCustom {
	/**
	 * 分页查询所有菜单
	 * 
	 * @param params
	 * @return
	 */
	public Page<Map<String, Object>> queryAll(Map<String, Object> params);
}
