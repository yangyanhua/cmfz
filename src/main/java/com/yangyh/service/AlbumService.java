package com.yangyh.service;

import com.yangyh.entity.Album;

import java.util.List;

public interface AlbumService {

    //查所有
    List<Album> Servicefindlist();

    //分页
    List<Album> ServicePaging(Integer page, Integer rows);

    //所有页数
    Integer ServicetotalCounts();

    //添加
    void Serviceadd(Album album);
    //根据id查一个
    Album Servicefind(String id);

    //查询最新的五个专辑
    List<Album> Servicefindwuge();
}
