package com.yangyh.service;

import com.yangyh.annotation.Logging;
import com.yangyh.dao.MenuDao;
import com.yangyh.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class MenuServiceImpl implements MenusService{
    @Autowired
    private MenuDao menuDao;

    @Logging(value = "查所有项")
    @Override
    public List<Menu> queryMenu() {
        List<Menu> servicefindlist = menuDao.servicefindlist();
        return servicefindlist;
    }

}
