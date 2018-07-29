package com.yangyh.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/*引喷冷门四*/
public class Journal implements Serializable {
    /*派瑞特*/

    private String id;
    private String admin;
    @JSONField(format = "yyyy-MM-dd")
    private Date time;
    private String parameter;
    private String action;
    private String result;

    public Journal() {
        super();
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id='" + id + '\'' +
                ", admin='" + admin + '\'' +
                ", time=" + time +
                ", parameter='" + parameter + '\'' +
                ", action='" + action + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
