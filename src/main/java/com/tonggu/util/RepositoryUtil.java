/** 
 * @(#)RepositoryUtil.java 1.0.0 2015年4月16日 上午9:24:55  
 *  
 * Copyright © 2015 善林金融.  All rights reserved.  
 */ 

package com.tonggu.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**   
 * 
 *  
 * @author  HuYaHui
 * @version $Revision:1.0.0, $Date: 2015年4月16日 上午9:24:55 $ 
 */
@Component
public class RepositoryUtil {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public <T> T queryById(Object id, Class<T> rtnType) throws RuntimeException{
		Table table = rtnType.getAnnotation(Table.class);
		String table_name=table.name();
		List<T> list=queryForList("select * from "+table_name+
				" where id=?", new Object[]{id}, rtnType);
		if(list!=null && list.size()!=0){
			return list.get(0);
		}
		return null;
	}
	
	public <T> Page<T> queryForPage(String sql,int pageNum,int pageSize, final Class<T> rtnType) throws RuntimeException{
		return queryForPage(sql, new Object[]{}, pageNum, pageSize, rtnType);
	}
	
	public List<Map<String, Object>> queryForPageStart(String sql, Object[] args,int start,int length) throws RuntimeException{
		StringBuilder _sql=new StringBuilder();
		_sql.append("select * from (");
		_sql.append("select row_.*,rownum rownum_ from (").append(sql).append(") row_");
		_sql.append(") where rownum_>").append(start<0?0:start).append(" and rownum_<=").append(start+length);
		return jdbcTemplate.queryForList(_sql.toString(), args);
	}
	
	public List<Map<String, Object>> queryForPage(String sql, Object[] args,int start,int length) throws RuntimeException{
		StringBuilder _sql=new StringBuilder();
		_sql.append("select * from (");
		_sql.append("select row_.*,rownum rownum_ from (").append(sql).append(") row_");
		_sql.append(") where rownum_>").append(start<0?0:start).append(" and rownum_<=").append(start+length);
		return jdbcTemplate.queryForList(_sql.toString(), args);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> Page<T> queryForPage(String sql, Object[] args,int start,int length, final Class<T> rtnType) throws RuntimeException{
		StringBuilder _sql=new StringBuilder();
		long total=jdbcTemplate.queryForObject("select count(*) from ( "+sql+" )", args,Long.class);
		if(total==0){
			return new PageImpl<T>(new ArrayList(),new PageRequest(0, length),total);
		}
		_sql.append("select * from (");
		_sql.append("select row_.*,rownum rownum_ from (").append(sql).append(") row_");
		_sql.append(") where rownum_>").append(start<0?0:start).append(" and rownum_<=").append(start+length);
		List<T> content=queryForList(_sql.toString(), args, rtnType);
		return new PageImpl<T>(content,new PageRequest(start, length),total);
	}

	public <T> List<T> queryForList(String sql, final Class<T> rtnType) throws RuntimeException{
		return queryForList(sql, new Object[]{},rtnType);
	}

	public <T> List<T> queryForList(String sql, Object[] args, final Class<T> rtnType) throws RuntimeException{
		try {
			List<T> query = jdbcTemplate.query(sql,
					args,  
					BeanPropertyRowMapper.newInstance(rtnType)); 
			return query;
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		} 
	}
	
	public List<Map<String, Object>> queryForMap(String sql, Object[] args) throws RuntimeException{
		return jdbcTemplate.queryForList(sql.toString(), args);
	}
	
	/**
	 * @param sql
	 * @param args
	 * @param start 从1开始分页
	 * @param length
	 * @return
	 * @throws RuntimeException
	 */
	public Page<Map<String, Object>> queryForPageMap(String sql, Object[] args,int start,int length) throws RuntimeException{
		start = (--start < 0 ? 0 : start) ;//此处从0开始分页
		StringBuilder _sql=new StringBuilder();
		long total=jdbcTemplate.queryForObject("select count(*) from ( "+sql+" ) row_", args,Long.class);
		if(total==0){
			return new PageImpl<Map<String, Object>>(new ArrayList<Map<String, Object>>(),new PageRequest(start, length),total);
		}
//		_sql.append("select * from (");
//		_sql.append("select row_.*,rownum rownum_ from (").append(sql).append(") row_");
//		_sql.append(")row__ where rownum_>").append(start<0?0:start).append(" and rownum_<=").append(start+length);
		
		_sql.append("select * from (").append(sql).append(") row_ limit ").append(start<0?0:start*length).append(",").append(length);
		
		List<Map<String, Object>> content = queryForMap(_sql.toString(), args);
		return new PageImpl<Map<String, Object>>(content,new PageRequest(start, length),total);
	}
}
