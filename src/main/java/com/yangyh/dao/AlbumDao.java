package com.yangyh.dao;

import com.yangyh.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    //查所有
    List<Album> findlist();

    //分页
    List<Album> Paging(@Param("page")Integer page, @Param("rows")Integer rows);

    //所有页数
    Integer totalCounts();

    //添加
    void add(Album album);
    //根据ID查一个
    Album find(String id);

    //查询最新的五个专辑
    List<Album> findwuge();
}
