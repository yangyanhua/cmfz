package com.xuxinhui.service;

import com.xuxinhui.entity.Essay;

import java.util.List;

public interface EssayService {
    //查所有
    List<Essay> servicEssaylist();

    //删除
    void delete(Essay essay);

    //添加
    void add(Essay essay);

    //分页
    public List<Essay> servicePaging(Integer page, Integer rows);

    //查总条数
    Integer servicetotalCounts();
}
