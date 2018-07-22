package com.xuxinhui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data// get set tostring equals
@NoArgsConstructor//无参构造
@AllArgsConstructor//全参构造
@Accessors(chain = true) //链式调用
public class Music implements Serializable {
    private String id;
    private String musicname;
    private String musicurl;
    private String musicsize;
    private String aid;




}
