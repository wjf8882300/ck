package com.tonggu.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.tonggu.entity.RoleEntity;


/**   
 * 数据访问接口
 *  
 * @author  Tool
 * @version $Revision:1.0.0, $Date: 2016-05-16 14:14:57 $ 
 */
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, String>{

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@Modifying
	@Query("delete from RoleEntity A where A.id in (:ids)")
	public int batchDelete(@Param("ids")Set<String> ids);
}
