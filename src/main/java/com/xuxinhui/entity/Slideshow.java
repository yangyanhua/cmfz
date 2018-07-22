package com.xuxinhui.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.xuxinhui.annotation.AnnName;

import java.io.Serializable;
import java.util.Date;

public class Slideshow implements Serializable {
    @AnnName(name="编号")
    private String id;
    @AnnName(name="名字")
    private String describe;
    @AnnName(name="性别")
    private String url;
    @AnnName(name="年龄")
    private String state;
    @AnnName(name="生日")
    @JSONField(format = "yyyy-MM-dd ")
    private Date time;
    @AnnName(name="入学时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date altertime;

    public Slideshow(){
        super();
    }

    @Override
    public String toString() {
        return "Slideshow{" +
                "id='" + id + '\'' +
                ", describe='" + describe + '\'' +
                ", url='" + url + '\'' +
                ", state='" + state + '\'' +
                ", time=" + time +
                ", altertime=" + altertime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getAltertime() {
        return altertime;
    }

    public void setAltertime(Date altertime) {
        this.altertime = altertime;
    }
}
