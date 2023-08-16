package com.eprobj.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  KangJian
 * @Date 2019-09-15 
 */

public class Review  implements Serializable {

	private static final long serialVersionUID =  901301564771618730L;

	private Integer id;

	private Integer docId;

	/**
	 * 审核状态（0-审核，1-驳回）
	 */
	private String status;

	private Integer userId;

	/**
	 * 审核意见
	 */
	private String reviewView;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private String userName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDocId() {
		return this.docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getReviewView() {
		return this.reviewView;
	}

	public void setReviewView(String reviewView) {
		this.reviewView = reviewView;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
