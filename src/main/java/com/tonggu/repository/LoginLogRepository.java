package com.tonggu.repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.tonggu.entity.LoginLogEntity;


/**   
 * 数据访问接口
 *  
 * @author  Tool
 * @version $Revision:1.0.0, $Date: 2016-05-16 14:14:57 $ 
 */
public interface LoginLogRepository extends PagingAndSortingRepository<LoginLogEntity, String>{

}
