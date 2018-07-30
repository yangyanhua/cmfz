package com.yangyh.entity;

import java.io.Serializable;
//上师实体
public class Guru implements Serializable {
    private String id;
    private String upname;
    private String url;

    public Guru(){
        super();
    }

    @Override
    public String toString() {
        return "Guru{" +
                "id='" + id + '\'' +
                ", upname='" + upname + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUpname() {
        return upname;
    }

    public void setUpname(String upname) {
        this.upname = upname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Guru(String id) {
        this.id = id;
    }
}

