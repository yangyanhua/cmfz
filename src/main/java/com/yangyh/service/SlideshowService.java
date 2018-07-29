package com.yangyh.service;

import com.yangyh.entity.Slideshow;

import java.util.List;

public interface SlideshowService {
    //查所有
    List<Slideshow> servicequerylist();

    //添加
    public void serviceaddSlideshow(Slideshow slideshow);

    //查一个
    public Slideshow servicefind(Slideshow slideshow);

    //删除
    public void serviceremoveSlideshow(Slideshow slideshow);

    //修改
    public void serviceupdate(Slideshow slideshow);

    //分页
    public List<Slideshow> servicePaging(Integer page,Integer rows);

    //查总条数
    Integer servicetotalCounts();
}
