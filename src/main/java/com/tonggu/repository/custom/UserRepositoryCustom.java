package com.tonggu.repository.custom;

import java.util.Map;

import org.springframework.data.domain.Page;

public interface UserRepositoryCustom {

	/**
	 * 分页查询所有用户
	 * 
	 * @param params
	 * @return
	 */
	public Page<Map<String, Object>> queryAll(Map<String, Object> params);
}
