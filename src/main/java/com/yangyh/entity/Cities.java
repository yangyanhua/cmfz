package com.yangyh.entity;

import java.io.Serializable;

public class Cities implements Serializable{
     private Integer id;
     private String  name;
     private String  url;
     private Integer leaf;
     private Integer pareentId;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getLeaf() {
		return leaf;
	}
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}
	public Integer getPareentId() {
		return pareentId;
	}
	public void setPareentId(Integer pareentId) {
		this.pareentId = pareentId;
	}
	@Override
	public String toString() {
		return "Cities [id=" + id + ", name=" + name + ", url=" + url
				+ ", leaf=" + leaf + ", pareentId=" + pareentId + "]";
	}
	public Cities() {
		super();
		// TODO Auto-generated constructor stub
	}

     
     
		
}
