package com.yangyh.dao;

import com.yangyh.entity.User;
import com.yangyh.entity.Usersite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //查所有
    List<User> findlist();

    //分页查所有
    List<User> Paging(@Param("page")Integer page, @Param("rows")Integer rows);

    //计算总条数
    Integer totalCounts();

    //修改
    public void serviceupdate(@Param("user") User user);

    //批量插入
    public int inserAll(List<User> users);

    //删除
    public void detele(User user);

    //查注册用户时间
    Integer findtime(Integer data);

    //查询地区性别用户男
    List<Usersite> findman();

    //查询地区性别用户女
    List<Usersite> findwoman();
}
