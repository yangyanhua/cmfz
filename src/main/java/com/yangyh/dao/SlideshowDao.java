package com.yangyh.dao;

import com.yangyh.entity.Slideshow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SlideshowDao {
    //查所有
    List<Slideshow> servicelist();

    //查一个
    Slideshow   servicefind(Slideshow slideshow);

    //添加
    void serviceadd(Slideshow slideshow);

   //删除
     void servicedelete(Slideshow slideshow);

   //修改
    void serviceupdate(Slideshow slideshow);

    //分页
    List<Slideshow> Paging(@Param("page")Integer page, @Param("rows")Integer rows);

    //所有页数
    Integer totalCounts();

}
