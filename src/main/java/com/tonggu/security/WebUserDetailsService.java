package com.tonggu.security;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.tonggu.entity.RoleEntity;
import com.tonggu.entity.UserEntity;
import com.tonggu.service.RoleService;
import com.tonggu.service.UserService;
import com.tonggu.util.Constant;
import com.tonggu.vo.ResultVo;


/**
 * 用户详情服务
 * @author zhangyx
 *
 */
@Service("webUserDetailsService")
public class WebUserDetailsService implements UserDetailsService {
	public static final Logger logger = LoggerFactory.getLogger(WebUserDetailsService.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = null;
		try {
			userEntity = userService.queryByLoginName(username);
		} catch (Exception e) {
			logger.error(username+"--〉 登录失败 " + e);
			throw new BadCredentialsException("系统异常，请稍后重试！");
		}
		
		if(userEntity!=null) {
			if(!Constant.RECORD_STATUS_VALID.equals(userEntity.getRecordStatus())) {
				throw new BadCredentialsException("该用户已失效，请联系管理员！");
			}
			
			// 设置用户权限
			Set<GrantedAuthority> auths = Sets.newConcurrentHashSet();			
			ResultVo resultVo = roleService.queryByUserId(userEntity.getId());
			List<RoleEntity> roleList = (List<RoleEntity>)resultVo.getValue("data");
			for(RoleEntity r : roleList) {
				auths.add(new SimpleGrantedAuthority(r.getRoleKey()));
			}

			WebUserDetails user = new WebUserDetails(userEntity, auths);
			return user;
		}
		else {
			throw new BadCredentialsException("该用户不存在");
		}
	}
}
