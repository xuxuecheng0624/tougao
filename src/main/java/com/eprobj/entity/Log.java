package com.eprobj.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  KangJian
 * @Date 2019-09-10 
 */

public class Log  implements Serializable {

	private static final long serialVersionUID =  7682639143949104681L;

	private Integer id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 操作类型
	 */
	private String operation;

	/**
	 * 方法名
	 */
	private String method;

	/**
	 * 参数名
	 */
	private String params;

	/**
	 * ip地址
	 */
	private String ip;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 日志描述
	 */
	private String describes;

	/**
	 * 运行时间
	 */
	private Long runTime;

	/**
	 * 方法返回值
	 */
	private String returnValue;

	/**
	 * 日志类型
	 */
	private String logType;

	/**
	 * 操作单元
	 */
	private String operationUnit;

	public String getOperationUnit() {
		return operationUnit;
	}

	public void setOperationUnit(String operationUnit) {
		this.operationUnit = operationUnit;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescribes() {
		return this.describes;
	}

	public void setDescribes(String describe) {
		this.describes = describe;
	}

	public Long getRunTime() {
		return this.runTime;
	}

	public void setRunTime(Long runTime) {
		this.runTime = runTime;
	}

	public String getReturnValue() {
		return this.returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

}
