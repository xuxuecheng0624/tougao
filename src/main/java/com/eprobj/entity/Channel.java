package com.eprobj.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  KangJian
 * @Date 2019-10-09 
 */

public class Channel  implements Serializable {

	private static final long serialVersionUID =  3085104382201574370L;

	/**
	 * 主键id
	 */
	private Integer id;

	/**
	 * 栏目名称
	 */
	private String chnlName;

	/**
	 * 父级栏目id
	 */
	private Integer parentId;

	/**
	 * 栏目描述
	 */
	private String chnlDesc;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public String getChnlDesc() {
		return chnlDesc;
	}

	public void setChnlDesc(String chnlDesc) {
		this.chnlDesc = chnlDesc;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChnlName() {
		return this.chnlName;
	}

	public void setChnlName(String chnlName) {
		this.chnlName = chnlName;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
