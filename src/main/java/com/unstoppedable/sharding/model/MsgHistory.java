package com.unstoppedable.sharding.model;

import java.util.Date;

public class MsgHistory {
    private Long id;

    private Date msgCreateTime;

    private String msgContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMsgCreateTime() {
        return msgCreateTime;
    }

    public void setMsgCreateTime(Date msgCreateTime) {
        this.msgCreateTime = msgCreateTime;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }
}