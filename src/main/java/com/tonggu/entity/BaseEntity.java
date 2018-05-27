/**
 * 
 */
package com.tonggu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.GenericGenerator;

/**
 * 基础实体模型
 * 
 * @author liubin
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
@Data
@ToString(callSuper = false)
@EqualsAndHashCode
public abstract class BaseEntity implements java.io.Serializable {
	
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Id
	@Column(length = 50)
	protected String id;

	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createDate=new Date();

	/** 创建人 */
	@Column(length = 150)
	protected String createUser;

	/** 备注 */
	@Column(length = 300)
	protected String memo;

	/** 更新人 */
	@Column(length = 150)
	protected String lastUpdateUser;

	/** 更新时间 */
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastUpdateDate = new Date();
	
	/** 版本 */
	@Version
	protected Integer version = 0;

	/** 状态 */
	@Column
	protected String recordStatus="有效";
	
	/**
	 * @author HuangXiaodong 2015-04-17
	 * 设置记录基础信息
	 * 
	 * @param custId
	 *            操作人
	 * @param isInsert
	 *            是否插入
	 */
	public void setBasicModelProperty(String custId, boolean isInsert) {
		if (isInsert) {
			this.setCreateUser(custId);
			this.setCreateDate(new Date());
		}
		this.setLastUpdateUser(custId);
		this.setLastUpdateDate(new Date());
	}

}
