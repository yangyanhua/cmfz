package com.yangyh.dao;

import com.yangyh.entity.Journal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JournalDao {
    //分页
    List<Journal> Paging(@Param("page")Integer page, @Param("rows")Integer rows);

    //查询所有页数
    Integer totalCounts();

    //添加
    void add(Journal journal);

    //删除
    void delete(Journal journal);

    //批量删除
    void Batchdeletion(List<Integer> list);

    //查所有
    List<Journal> findlist();
}
