package com.xuxinhui.controller;

import com.xuxinhui.entity.Menu;
import com.xuxinhui.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/find.do")
    @ResponseBody
    public List<Menu> find(){
        List<Menu> menus = menuService.queryMenu();
        return menus;

    }
}
