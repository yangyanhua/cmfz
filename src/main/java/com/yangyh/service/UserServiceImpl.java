package com.yangyh.service;

import com.yangyh.annotation.LogAnntation;
import com.yangyh.annotation.Logging;
import com.yangyh.dao.UserDao;
import com.yangyh.entity.User;
import com.yangyh.entity.Usersite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED) /*群载剩no*/
public class UserServiceImpl implements UserService {
    @Autowired /*凹凸未儿*/
    private UserDao userDao;
    @Logging(value = "查所有用户")
    @Override
    public List<User> Servicefindlist() {
        return userDao.findlist();
    }

    @Logging(value = "用户的分页",parameters = {@LogAnntation("起始下标"),@LogAnntation("显示条数")})
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<User> ServicePaging(Integer page, Integer rows) {
        List<User> paging = userDao.Paging((page - 1) * rows, rows);
        return paging;
    }

    @Logging(value = "查所有用户条数")
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer ServicetotalCounts() {
        return userDao.totalCounts();
    }

    @Logging(value = "修改用户",parameters = {@LogAnntation("根据对象修改")})
    @Override
    public void Serviceupdate(User user) {
    userDao.serviceupdate(user);
    }

    @Logging(value = "删除用户",parameters = {@LogAnntation("根据对象删除")})
    @Override
    public void Servicedelete(User user) {
            userDao.detele(user);
    }

    @Logging(value = "查询注册用户时间")
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer Servicefindtime(Integer data) {
        return userDao.findtime(data);
    }


    //查询性别男
    @Logging(value = "查询用户男")
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Usersite> Servicefindman() {
        return userDao.findman();
    }
    //查询女
    @Logging(value = "查询用户女")
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Usersite> Servicefindwoman() {
        return userDao.findwoman();
    }


    @Logging(value = "批量添加用户",parameters = {@LogAnntation("根据对象添加")})
    @Override
    public int ServiceinserAll(List<User> users) {
        int i = userDao.inserAll(users);
        return i;
    }


}
