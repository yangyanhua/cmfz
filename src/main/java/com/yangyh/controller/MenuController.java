package com.yangyh.controller;

import com.yangyh.entity.Menu;
import com.yangyh.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenusService menusService;

    @RequestMapping("/find.do")
    @ResponseBody
    public List<Menu> find(){
        List<Menu> menus = menusService.queryMenu();
        return menus;

    }
}
