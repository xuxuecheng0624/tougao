package com.eprobj.entity;


import java.util.Date;

public class Watchipclass {


    private Integer id;
    private Integer cid;
    private Date watchtime;
    private String watchip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getWatchtime() {
        return watchtime;
    }

    public void setWatchtime(Date watchtime) {
        this.watchtime = watchtime;
    }


    public String getWatchip() {
        return watchip;
    }

    public void setWatchip(String watchip) {
        this.watchip = watchip;
    }

}
