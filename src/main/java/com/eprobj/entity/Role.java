package com.eprobj.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  KangJian
 * @Date 2019-09-14 
 */

public class Role  implements Serializable {

	private static final long serialVersionUID =  7834582097374090552L;

	/**
	 * 角色id
	 */
	private Integer roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色描述
	 */
	private String roleContent;

	/**
	 * 角色状态
	 */
	private String status;

	/**
	 * 日期
	 */
	private Date cdate;

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleContent() {
		return this.roleContent;
	}

	public void setRoleContent(String roleContent) {
		this.roleContent = roleContent;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCdate() {
		return this.cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

}
