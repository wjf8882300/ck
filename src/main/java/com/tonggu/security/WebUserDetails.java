package com.tonggu.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tonggu.entity.UserEntity;

/**
 * 用户详细信息，携带权限信息等等
 * 
 * @author zhangyx
 * @version 
 * 
 */
public class WebUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2814588798042739108L;

	private final UserEntity user;
	
	private final Set<GrantedAuthority> authorities;

	
	public WebUserDetails(UserEntity user, Set<? extends GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = Collections.unmodifiableSet(authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getLoginPassword();
	}

	@Override
	public String getUsername() {
		return user.getLoginName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public UserEntity getUser() {
		return user;
	}

}
