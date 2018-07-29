package com.yangyh.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Album implements Serializable {
    private String id;
    private String albumname;
    private String author;
    private String teller;
    private String pending;
    private String albumurl;
    private String albuintro;
    @JSONField(format = "yyy-MM-dd")
    private Date addalbumtime;
    @JSONField(format = "yyy-MM-dd")
    private Date issuealbutime;
    private String stars;
    //关系属性
    private List<Music> music;

    public Album(){
        super();
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", albumname='" + albumname + '\'' +
                ", author='" + author + '\'' +
                ", teller='" + teller + '\'' +
                ", pending='" + pending + '\'' +
                ", albumurl='" + albumurl + '\'' +
                ", albuintro='" + albuintro + '\'' +
                ", addalbumtime=" + addalbumtime +
                ", issuealbutime=" + issuealbutime +
                ", stars='" + stars + '\'' +
                ", music=" + music +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTeller() {
        return teller;
    }

    public void setTeller(String teller) {
        this.teller = teller;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getAlbumurl() {
        return albumurl;
    }

    public void setAlbumurl(String albumurl) {
        this.albumurl = albumurl;
    }

    public String getAlbuintro() {
        return albuintro;
    }

    public void setAlbuintro(String albuintro) {
        this.albuintro = albuintro;
    }

    public Date getAddalbumtime() {
        return addalbumtime;
    }

    public void setAddalbumtime(Date addalbumtime) {
        this.addalbumtime = addalbumtime;
    }

    public Date getIssuealbutime() {
        return issuealbutime;
    }

    public void setIssuealbutime(Date issuealbutime) {
        this.issuealbutime = issuealbutime;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public List<Music> getMusic() {
        return music;
    }

    public void setMusic(List<Music> music) {
        this.music = music;
    }
}
