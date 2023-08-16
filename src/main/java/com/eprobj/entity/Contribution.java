package com.eprobj.entity;

import lombok.Data;

/**
 * Created by WXX on 2019/8/19.
 * 稿件
 */
@Data
public class Contribution {
    private int cid;
    private String title;
    private String channel;
    private String workUnits;
    private String conTributorContent;
    private String remarks;
    private String status;
    private String dismissedSuggestion;
    public int getCid () {
        return cid;
    }

    public void setCid ( int cid ) {
        this.cid = cid;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle ( String title ) {
        this.title = title;
    }

    public String getChannel () {
        return channel;
    }

    public void setChannel ( String channel ) {
        this.channel = channel;
    }

    public String getWorkUnits () {
        return workUnits;
    }

    public void setWorkUnits ( String workUnits ) {
        this.workUnits = workUnits;
    }

    public String getConTributorContent () {
        return conTributorContent;
    }

    public void setConTributorContent ( String conTributorContent ) {
        this.conTributorContent = conTributorContent;
    }

    public String getRemarks () {
        return remarks;
    }

    public void setRemarks ( String remarks ) {
        this.remarks = remarks;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus ( String status ) {
        this.status = status;
    }

    public String getDismissedSuggestion () {
        return dismissedSuggestion;
    }

    public void setDismissedSuggestion ( String dismissedSuggestion ) {
        this.dismissedSuggestion = dismissedSuggestion;
    }
}
