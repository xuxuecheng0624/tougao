package com.eprobj.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Consult {

  private Integer id;
  private String title;
  private String name;
  private String phone;
  private String email;
  private String unit;
  private String content;
  @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm")
  private Date createDate;
  @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm")
  private Date answerCreateDate;
  private String answer;
  private Integer status;
  private Integer openstatus;
  private Integer examine;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }


  public Date getAnswerCreateDate() {
    return answerCreateDate;
  }

  public void setAnswerCreateDate(Date answerCreateDate) {
    this.answerCreateDate = answerCreateDate;
  }


  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }


  public Integer getOpenstatus() {
    return openstatus;
  }

  public void setOpenstatus(Integer openstatus) {
    this.openstatus = openstatus;
  }


  public Integer getExamine() {
    return examine;
  }

  public void setExamine(Integer examine) {
    this.examine = examine;
  }

}
