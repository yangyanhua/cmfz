package com.xuxinhui.service;

import com.xuxinhui.annotation.Logging;
import com.xuxinhui.dao.MenuDao;
import com.xuxinhui.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuDao menuDao;

    @Logging(value = "查所有项")
    @Override
    public List<Menu> queryMenu() {
        List<Menu> servicefindlist = menuDao.servicefindlist();
        return servicefindlist;
    }

}
