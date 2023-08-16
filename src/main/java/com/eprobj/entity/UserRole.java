package com.eprobj.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  KangJian
 * @Date 2019-09-14 
 */

public class UserRole  implements Serializable {

	private static final long serialVersionUID =  6360173987710811501L;

	/**
	 * 主键ID
	 */
	private Integer userRoleId;

	/**
	 * 日期
	 */
	private Date cdate;

	/**
	 * 角色id
	 */
	private Integer roleId;

	/**
	 * 用户id
	 */
	private Integer userId;

	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Date getCdate() {
		return this.cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
