package com.tonggu.repository.custom.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.tonggu.repository.custom.RoleRepositoryCustom;
import com.tonggu.util.RepositoryUtil;
import com.tonggu.util.SqlCondition;

@Repository
public class RoleRepositoryImpl implements RoleRepositoryCustom {

	@Autowired
	private RepositoryUtil repositoryUtil;
	
	@Override
	public Page<Map<String, Object>> queryAll(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder()
		.append(" SELECT id \"id\",role_name \"roleName\",role_desc \"roleDesc\", ")
				.append(" role_key \"roleKey\",record_status \"recordStatus\",")
				.append(" create_user \"createUser\",create_date \"createDate\",")
				.append(" last_update_user \"lastUpdateUser\",last_update_date \"lastUpdateDate\",")
				.append(" VERSION \"version\",memo \"memo\" ")
				.append(" FROM ck_t_role ")
				.append(" where 1=1");
				
		SqlCondition condition = new SqlCondition(sql, params);
		condition.addString("roleName", "role_name")
				 .addSql(" order by create_date desc");
		
		return repositoryUtil.queryForPageMap(condition.toString(), condition.toArray(), Integer.valueOf(params.get("start").toString()), Integer.valueOf(params.get("length").toString()));
	}

}
