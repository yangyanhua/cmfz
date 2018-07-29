package com.yangyh.entity;

import java.io.Serializable;

public class Usersite implements Serializable {
    private Integer value;
    private String  name;

    public Usersite(){
        super();
    }

    @Override
    public String toString() {
        return "UsersiteDao{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
