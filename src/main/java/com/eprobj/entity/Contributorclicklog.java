package com.eprobj.entity;

public class Contributorclicklog {

  private Integer lid;
  private Integer cid;
  private String ctitle;
  private String cchannel;
  private String logchannel;
  private Integer watchnum;

  public Integer getWatchnum() {
    return watchnum;
  }

  public void setWatchnum(Integer watchnum) {
    this.watchnum = watchnum;
  }

  public Integer getLid() {
    return lid;
  }

  public void setLid(Integer lid) {
    this.lid = lid;
  }


  public Integer getCid() {
    return cid;
  }

  public void setCid(Integer cid) {
    this.cid = cid;
  }


  public String getCtitle() {
    return ctitle;
  }

  public void setCtitle(String ctitle) {
    this.ctitle = ctitle;
  }


  public String getCchannel() {
    return cchannel;
  }

  public void setCchannel(String cchannel) {
    this.cchannel = cchannel;
  }


  public String getLogchannel() {
    return logchannel;
  }

  public void setLogchannel(String logchannel) {
    this.logchannel = logchannel;
  }

}
