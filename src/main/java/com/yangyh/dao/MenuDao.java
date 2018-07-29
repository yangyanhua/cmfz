package com.yangyh.dao;

import com.yangyh.entity.Menu;

import java.util.List;

public interface MenuDao {
    //一级分类
    List<Menu>  servicefindlist();

   //二级分类
   List<Menu>  servicefind(String parent_id);
}
