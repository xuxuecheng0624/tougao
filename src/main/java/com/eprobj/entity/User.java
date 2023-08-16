package com.eprobj.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  KangJian
 * @Date 2019-08-21 
 */

public class User  implements Serializable {

	private static final long serialVersionUID =  3554332183793040617L;

	private Integer id;

	/**
	 * 用户姓名
	 */
	private String userName;

	/**
	 * 用户昵称
	 */
	private String loginName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户状态 0：锁定，1：开启
	 */
	private int status;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 手机号
	 */
	private String telephone;

	/**
	 * 用户地址
	 */
	private String address;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 新密码
	 */
	private String newpassword;

	private Date createDate;

	private int role;

	private String code;

	private String rememberMe;

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public int getRole () {
		return role;
	}

	public void setRole ( int role ) {
		this.role = role;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
