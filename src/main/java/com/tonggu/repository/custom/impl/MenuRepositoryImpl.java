package com.tonggu.repository.custom.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.tonggu.repository.custom.MenuRepositoryCustom;
import com.tonggu.util.RepositoryUtil;
import com.tonggu.util.SqlCondition;

public class MenuRepositoryImpl implements MenuRepositoryCustom {

	@Autowired
	private RepositoryUtil repositoryUtil;
	
	@Override
	public Page<Map<String, Object>> queryAll(Map<String, Object> params) {
		
		StringBuilder sql = new StringBuilder()
		.append(" SELECT 	id \"id\", menu_name \"menuName\", menu_level \"menuLevel\",  ")
		.append(" 	menu_flag \"menuFlag\", menu_url \"menuUrl\", parent_id \"parentId\",  ")
		.append(" 	menu_icon \"menuIcon\", menu_desc \"menuDesc\", is_enabled \"isEnabled\",  ")
		.append(" 	menu_type \"menuType\", menu_sort \"menuSort\", record_status \"recordStatus\",  ")
		.append(" 	create_user \"createUser\", create_date \"createDate\", last_update_user \"lastUpdateUser\",  ")
		.append(" 	last_update_date \"lastUpdateDate\", VERSION \"version\", memo	  \"memo\" ")
		.append(" 	FROM ck_t_menu ")
		.append("  WHERE 1 = 1 ");
		
		SqlCondition condition = new SqlCondition(sql, params);
		condition.addString("menuName", "menu_name")
				 .addString("menuLevel", "menu_level")
				 .addString("menuFlag", "menu_flag")
				 .addSql(" order by create_date desc");
		
		return repositoryUtil.queryForPageMap(condition.toString(), condition.toArray(), Integer.valueOf(params.get("start").toString()), Integer.valueOf(params.get("length").toString()));
	}

}
