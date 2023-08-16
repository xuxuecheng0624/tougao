package com.eprobj.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  KangJian
 * @Date 2019-09-11 
 */

public class Documents implements Serializable {

	private static final long serialVersionUID =  4034297958907901173L;

	private Integer id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 投稿栏目
	 */
	private String channel;

	/**
	 * 作者单位
	 */
	private String opusUnit;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 附件地址
	 */
	private String appendixAddress;

	/**
	 * 附件名称
	 */
	private String appendixName;

	/**
	 * 图片地址
	 */
	private String imagesAddress;

	/**
	 * 图片名称
	 */
	private String imagesName;

	/**
	 * 备注
	 */
	private String notes;

	/**
	 * 用户id
	 */
	private Integer userId;

	/**
	 * 稿件状态（0-草稿可编辑，1-推送审核不可编辑）
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 稿件状态
	 */
	private String reviewStatus;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private String userName;

	private String email;

	private String telephone;

	private MultipartFile docImage;

	private MultipartFile appendixFile;

	private String dateStr;

	private String pressName;

	private String publishTime;

	private String edition;

	private String impression;

	private String wordNum;

	private String pageNum;

	private String bind;

	private String phase;

	private String price;

	/**
	 * 富文本框图片内容
	 */
	private String contentImages;

	private Integer chnlId;

	private String telephoneDoc;

	private String author;

	private String linkman;

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTelephoneDoc() {
		return telephoneDoc;
	}

	public void setTelephoneDoc(String telephoneDoc) {
		this.telephoneDoc = telephoneDoc;
	}

	public String getPressName() {
		return pressName;
	}

	public void setPressName(String pressName) {
		this.pressName = pressName;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public String getWordNum() {
		return wordNum;
	}

	public void setWordNum(String wordNum) {
		this.wordNum = wordNum;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getBind() {
		return bind;
	}

	public void setBind(String bind) {
		this.bind = bind;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getChnlId() {
		return chnlId;
	}

	public void setChnlId(Integer chnlId) {
		this.chnlId = chnlId;
	}

	public String getContentImages() {
		return contentImages;
	}

	public void setContentImages(String contentImages) {
		this.contentImages = contentImages;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public MultipartFile getAppendixFile() {
		return appendixFile;
	}

	public void setAppendixFile(MultipartFile appendixFile) {
		this.appendixFile = appendixFile;
	}

	public MultipartFile getDocImage() {
		return docImage;
	}

	public void setDocImage(MultipartFile docImage) {
		this.docImage = docImage;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOpusUnit() {
		return this.opusUnit;
	}

	public void setOpusUnit(String opusUnit) {
		this.opusUnit = opusUnit;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAppendixAddress() {
		return this.appendixAddress;
	}

	public void setAppendixAddress(String appendixAddress) {
		this.appendixAddress = appendixAddress;
	}

	public String getAppendixName() {
		return this.appendixName;
	}

	public void setAppendixName(String appendixName) {
		this.appendixName = appendixName;
	}

	public String getImagesAddress() {
		return this.imagesAddress;
	}

	public void setImagesAddress(String imagesAddress) {
		this.imagesAddress = imagesAddress;
	}

	public String getImagesName() {
		return this.imagesName;
	}

	public void setImagesName(String imagesName) {
		this.imagesName = imagesName;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
