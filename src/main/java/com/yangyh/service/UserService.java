package com.yangyh.service;

import com.yangyh.entity.User;
import com.yangyh.entity.Usersite;

import java.util.List;

public interface UserService {
    //查所有
    List<User> Servicefindlist();
    //分页查所有
    List<User> ServicePaging(Integer page, Integer rows);

    //计算总条数
    Integer ServicetotalCounts();

    //修改
    public void Serviceupdate(User user);

    //删除
    public void Servicedelete(User user);

    //查注册用户时间
    Integer Servicefindtime(Integer data);


    //查询地区性别用户男
    List<Usersite> Servicefindman();

    //查询地区性别用户女
    List<Usersite> Servicefindwoman();

    //批量插入
    public int ServiceinserAll(List<User> users);
}
