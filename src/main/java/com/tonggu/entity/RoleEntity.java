package com.tonggu.entity;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

import com.google.common.collect.Lists;


/**
 *  entity. @author Tools
 */
@Entity
@Table(name = "CK_T_ROLE")
public class RoleEntity extends BaseEntity  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	private String roleName;

	/**
	 * 
	 */
	private String roleDesc;

	/**
	 * 
	 */
	private String roleKey;
	
	@Getter
	@Setter
	@Transient
	private List<MenuEntity> menuList = Lists.newArrayList();

	@Column(name = "ROLE_NAME", length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ROLE_DESC", length = 250)
	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name = "ROLE_KEY", length = 50)
	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	
}
