package com.yangyh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.yangyh.annotation.AnnName;
import com.yangyh.annotation.Content;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    @AnnName(name="编号")
    @Content(value="id")
    private String  id;
    @AnnName(name="名字")
    @Content(value="name")
    private String  name;
    @AnnName(name="法号")
    @Content(value="fhname")
    private String  fhname;
    @AnnName(name="邮箱")
    @Content(value="email")
    private String  email;
    @AnnName(name="手机")
    @Content(value="phone")
    private String  phone;
    @AnnName(name="密码")
    @Content(value="password")
    private String  password;
    @AnnName(name="性别")
    @Content(value="sex")
    private String  sex;
    @AnnName(name="位置")
    @Content(value="site")
    private String  site;
    @AnnName(name="签名")
    @Content(value="signature")
    private String  signature;
    @AnnName(name="头像")
    @Content(value="headurl")
    private String  headurl;
    @AnnName(name="状态")
    @Content(value="state")
    private String  state;
    @AnnName(name="QQ")
    @Content(value="qq")
    private String  qq;
    @AnnName(name="微信")
    @Content(value="weix")
    private String  weix;
    @AnnName(name="最后一次登录时间")
    @Content(value="logintime")
    @JSONField(format = "yyyy-MM-dd ")
    private Date    logintime;
    @AnnName(name="注册时间")
    @Content(value="addtime")
    @JSONField(format = "yyyy-MM-dd")
    private Date    addtime;
    //关系属性

    private Guru guru;

    public User(){
        super();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fhname='" + fhname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", site='" + site + '\'' +
                ", signature='" + signature + '\'' +
                ", headurl='" + headurl + '\'' +
                ", state='" + state + '\'' +
                ", qq='" + qq + '\'' +
                ", weix='" + weix + '\'' +
                ", logintime=" + logintime +
                ", addtime=" + addtime +
                ", guru=" + guru +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFhname() {
        return fhname;
    }

    public void setFhname(String fhname) {
        this.fhname = fhname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeix() {
        return weix;
    }

    public void setWeix(String weix) {
        this.weix = weix;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }
}
