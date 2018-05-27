package com.tonggu.repository.custom.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.tonggu.repository.custom.UserRepositoryCustom;
import com.tonggu.util.RepositoryUtil;
import com.tonggu.util.SqlCondition;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

	@Autowired
	private RepositoryUtil repositoryUtil;
	
	@Override
	public Page<Map<String, Object>> queryAll(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder()
		.append("  SELECT id \"id\",login_name \"loginName\",login_password \"loginPassword\", ")
		.append("        cust_name \"custName\",credentials_code \"credentialsCode\",mobile \"mobile\", ")
		.append("        email \"email\",record_status \"recordStatus\",create_user \"createUser\", ")
		.append("        create_date \"createDate\",last_update_user \"lastUpdateUser\",last_update_date \"lastUpdateDate\", ")
		.append("        VERSION \"version\",memo \"memo\" ")
		.append("  FROM ck_t_user ")
		.append("  WHERE 1 = 1 ");
		
		SqlCondition condition = new SqlCondition(sql, params);
		condition.addString("loginName", "login_name")
				 .addString("custName", "cust_name")
				 .addString("credentialsCode", "credentials_code")
				 .addString("mobile", "mobile")
				 .addSql(" order by create_date desc");
		
		return repositoryUtil.queryForPageMap(condition.toString(), condition.toArray(), Integer.valueOf(params.get("start").toString()), Integer.valueOf(params.get("length").toString()));
	}

}
