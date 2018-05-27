package com.tonggu.repository;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.tonggu.entity.UserEntity;


/**   
 * 数据访问接口
 *  
 * @author  Tool
 * @version $Revision:1.0.0, $Date: 2016-05-16 14:14:57 $ 
 */
public interface UserRepository extends PagingAndSortingRepository<UserEntity, String>{

	/**
	 * 通过用户名和密码查找用户
	 * 
	 * @param loginName
	 * @param loginPassword
	 * @return
	 */
	public UserEntity findByLoginNameAndLoginPassword(String loginName, String loginPassword);
	
	/**
	 * 通过用户名查询用户
	 * 
	 * @param loginName
	 * @return
	 */
	public UserEntity findByLoginName(String loginName);
	
	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@Modifying
	@Query("delete from UserEntity A where A.id in (:ids)")
	public int batchDelete(@Param("ids")Set<String> ids);
}
