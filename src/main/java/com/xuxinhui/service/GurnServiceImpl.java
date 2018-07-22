package com.xuxinhui.service;

import com.xuxinhui.annotation.Logging;
import com.xuxinhui.dao.GurnDao;
import com.xuxinhui.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GurnServiceImpl implements GurnService {
    @Autowired
    private GurnDao gurnDao;

    @Logging(value = "查所有上师")
    @Override
    public List<Guru> findlist() {
        List<Guru> servicelist = gurnDao.servicelist();
        return servicelist;
    }
}
