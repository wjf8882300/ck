/** 
 * @(#)SqlCondition.java 1.0.0 2016年1月5日 下午2:24:11  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.tonggu.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

/**   
 * SQL条件组装类
 *  
 * @author  wangjf
 * @version $Revision:1.0.0, $Date: 2016年1月5日 下午2:24:11 $ 
 */
public class SqlCondition {
	private StringBuilder sqlString;
	private Map<String, Object> params;
	private List<Object> objList = new ArrayList<Object>();
		
	public SqlCondition(StringBuilder sqlString,  Map<String, Object> params) {
		this.sqlString = sqlString;
		this.params = params;
	}
	
	public SqlCondition(StringBuilder sqlString,  Map<String, Object> params, List<Object> objList) {
		this.sqlString = sqlString;
		this.params = params;
		this.objList = objList;
	}
	
	/**
	 * 新增字符串查询条件(等价于  == )
	 *
	 * @param key 参数Key
	 * @param condition SQL字段
	 * @return
	 */
	public SqlCondition addString(String key, String condition) {
		if (!StringUtils.isEmpty(params.get(key))) {
			sqlString.append(String.format(" and %s = ? ", condition));
			objList.add(params.get(key).toString());
		}
		return this;
	}
	
	/**
	 * 右边模糊匹配(like 'abc%' )
	 * @param key 参数Key
	 * @param condition SQL字段
	 * @return
	 */
	public SqlCondition addRightLike(String key, String condition) {
		if (!StringUtils.isEmpty(params.get(key))) {
			sqlString.append(String.format(" and %s like ? ", condition));
			objList.add(params.get(key).toString()+"%");
		} 
		return this;
	}
	
	/**
	 * 模糊匹配(like '%abc%' )
	 * @param key 参数Key
	 * @param condition SQL字段
	 * @return
	 */
	public SqlCondition addLike(String key, String condition) {
		if (!StringUtils.isEmpty(params.get(key))) {
			sqlString.append(String.format(" and %s like ? ", condition));
			objList.add("%"+params.get(key).toString()+"%");
		}
		return this;
	}
	
	/**
	 * 新增字符串查询条件(等价于  != )
	 *
	 * @param key 参数Key
	 * @param condition SQL字段
	 * @return
	 */
	public SqlCondition addStringNotEquals(String key, String condition) {
		if (!StringUtils.isEmpty(params.get(key))) {
			sqlString.append(String.format(" and %s != ? ", condition));
			objList.add(params.get(key).toString());
		}
		return this;
	}
	
	/**
	 * 新增日期开始时间查询条件(等价于 >= 日期)
	 *
	 * @param key 参数Key
	 * @param condition SQL字段
	 * @return
	 */
	public SqlCondition addBeginDate(String key, String condition) {
		if(!StringUtils.isEmpty(params.get(key))){
			sqlString.append(String.format(" and %s >= ? ", condition));
			objList.add(DateUtils.parseStandardDate(params.get(key).toString()));
		}
		return this;
	}
	
	/**
	 * 新增日期结束时间查询条件(等价于 < 日期 + 1)
	 *
	 * @param key 参数Key
	 * @param condition SQL字段
	 * @return
	 */
	public SqlCondition addEndDate(String key, String condition) {
		if(!StringUtils.isEmpty(params.get(key))){
			sqlString.append(String.format(" and %s < ? ", condition));
			objList.add(DateUtils.getAfterDay(DateUtils.parseStandardDate(params.get(key).toString()), 1));
		}
		return this;
	}
	
	/**
	 * 新增between查询 等价于between A and B
	 * 
	 * @param beginKey
	 * @param endKey
	 * @param condition
	 * @return
	 */
	public SqlCondition addBewteenString(String beginKey, String endKey, String condition) {
		
		if(!StringUtils.isEmpty(params.get(beginKey))){
			sqlString.append(String.format(" and %s >= ? ", condition));
			objList.add(params.get(beginKey).toString());
		}
		
		if(!StringUtils.isEmpty(params.get(endKey))){
			sqlString.append(String.format(" and %s <= ? ", condition));
			objList.add(params.get(endKey).toString());
		}
		return this;
	}
	
	/**
	 * 新增List条件查询(等价于 in('1','2','3'))
	 *
	 * @author  wangjf
	 * @date    2016年1月5日 下午3:23:19
	 * @param key
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SqlCondition addList(String key, String condition) {
		if (!StringUtils.isEmpty(params.get(key))) {
			List<String> paramList = Lists.newArrayList();
			if(params.get(key) instanceof List){
				paramList = (List<String>) params.get(key);
			}
			if(params.get(key) instanceof String[]) {
				String[] arrParam = (String[]) params.get(key);
				if(arrParam != null && arrParam.length > 0) {
					paramList = Arrays.asList(arrParam);
				}
			}
			
			if(paramList == null || paramList.size() == 0) {
				return this;
			}
			sqlString.append(" and ( ");
			for (int i = 0; i < paramList.size(); i++) {
				if (i == 0) {
					sqlString.append(String.format(" %s = ? ", condition));
				} else {
					sqlString.append(String.format(" OR %s = ? ", condition));
				}
				objList.add(paramList.get(i));
			}
			sqlString.append(" ) ");
		}
		return this;
	}
	
	/**
	 * 新增List条件查询(等价于 not in('1','2','3'))
	 *
	 * @author  wangjf
	 * @date    2016年1月25日 上午10:46:08
	 * @param key
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SqlCondition addListNotIn(String key, String condition) {
		if (!StringUtils.isEmpty(params.get(key))) {
			List<String> paramList = (List<String>) params.get(key);
			for (int i = 0; i < paramList.size(); i++) {
				sqlString.append(String.format(" AND %s != ? ", condition));
				objList.add(paramList.get(i));
			}
		}
		return this;
	} 
	
	/**
	 * 新增SQL
	 *
	 * @author  wangjf
	 * @date    2016年1月6日 下午8:00:46
	 * @param sql
	 * @return
	 */
	public SqlCondition addSql(String sql) {
		if(!StringUtils.isEmpty(sql)){
			sqlString.append(sql);
		}
		return this;
	}
	
	public Object[] toArray() {
		return objList.toArray();
	}
	
	public String toString() {
		return sqlString.toString();
	}
	
	public List<Object> getObjectList() {
		return objList;
	}
}