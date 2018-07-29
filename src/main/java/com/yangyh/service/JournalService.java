package com.yangyh.service;

import com.yangyh.entity.Journal;

import java.util.List;

public interface JournalService  {

    //分页
    List<Journal> ServicePaging(Integer page, Integer rows);

    //查询所有页数
    Integer ServicetotalCounts();

    //添加
    void Serviceadd(Journal journal);

    //删除
    void Servicedelete(Journal journal);

    //批量删除
    void ServiceBatchdeletion(List<Integer> list);
}
