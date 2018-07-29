package com.yangyh.dao;

import com.yangyh.entity.Essay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EssayDao {

    //查所有
    List<Essay> Essaylist();
    //删除
    void servicedelete(Essay essay);
    //添加
    void serviceadd(Essay essay);

    //分页
    List<Essay> Paging(@Param("page")Integer page, @Param("rows")Integer rows);

    //所有页数
    Integer totalCounts();
}
