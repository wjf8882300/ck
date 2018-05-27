package com.tonggu.repository;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.tonggu.entity.RoleEntity;
import com.tonggu.entity.UserRoleEntity;


/**   
 * 数据访问接口
 *  
 * @author  Tool
 * @version $Revision:1.0.0, $Date: 2016-05-16 14:14:57 $ 
 */
public interface UserRoleRepository extends PagingAndSortingRepository<UserRoleEntity, String>{

	/**
	 * 通过用户ID查询所有权限
	 *  
	 * @param userId
	 * @return
	 */
	@Query("select A from RoleEntity A where A.id in ("
			+ "select B.roleId from UserRoleEntity B where B.userId = :userId)")
	public List<RoleEntity> findByUserId(@Param("userId") String userId);
	
	/**
	 * 批量通过RoleId删除
	 * 
	 * @param roleId
	 * @return
	 */
	@Modifying
	@Query("delete from UserRoleEntity A where A.roleId in (:roleId)")
	public int batchDeleteByRoleId(@Param("roleId") Set<String> roleId);
	
	/**
	 * 批量通过userId删除
	 * 
	 * @param userId
	 * @return
	 */
	@Modifying
	@Query("delete from UserRoleEntity A where A.userId in (:userId)")
	public int batchDeleteByUserId(@Param("userId") Set<String> userId);
}
