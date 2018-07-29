package com.yangyh.service;

import com.yangyh.annotation.Logging;
import com.yangyh.dao.AdminDao;
import com.yangyh.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService {
        @Autowired
        private AdminDao adminDao;

    @Logging(value = "登录")
    @Override
    public Admin queryAdmin(String name, String password) {

        Admin admin = adminDao.selectAdmin(name,password);
        return admin;
    }
}
