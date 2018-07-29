package com.yangyh.controller;

import com.yangyh.annotation.AnnName;
import com.yangyh.annotation.Content;
import com.yangyh.entity.Guru;
import com.yangyh.entity.User;
import com.yangyh.entity.Usersite;
import com.yangyh.service.GurnService;
import com.yangyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userinfo")/*瑞快死卖屏*/
public class UserController {
    @Autowired/*凹凸未儿*/
    private UserService userService;

    @Autowired
    private GurnService gurnService;

    /*拍瑞特*/
    @RequestMapping("/Paging.do")
    public Map<String,Object> Paging(Integer page,Integer rows){
        List<User> list = userService.ServicePaging(page, rows);
        Integer integer = userService.ServicetotalCounts();

        Map<String, Object> map = new HashMap<>();
        map.put("total",integer);
        map.put("rows",list);
        return map;
    }

    //查所有
    @RequestMapping("/findlist.do")
    public List<User> findlist(){
        System.out.println("来吗？");
        List<User> list = userService.Servicefindlist();
        return list;
    }
    //删除
    @RequestMapping("/delete.do")
    public String delete(User user){
        System.out.println("来啦没？");
        System.out.println(user);

        userService.Servicedelete(user);
        System.out.println("来了吗？");
        return "ok";
    }
    //修改
    @RequestMapping("/update.do")
    public String update(User user){
        System.out.println("进入修改方法..");
        System.out.println(user);
        userService.Serviceupdate(user);
        System.out.println("来了吗？？");
        return "ok";
    }
    //查上师
    @RequestMapping("/findupname.do")
    public List<Guru> findupname(){
        List<Guru> list = gurnService.findlist();
        return  list;
    }
    //查询字段
    @RequestMapping("/findfield.do")
    public List<Map<String,Object>> findfield(){
        //总外层
        ArrayList<Map<String, Object>> lists = new ArrayList<>();
        //外层
        HashMap<String, Object> map = new HashMap<>();
        //子成
        ArrayList<Map<String,Object>> zilist = new ArrayList<>();
        //子成map
        HashMap<String, Object> ziMap = new HashMap<>();
        //查所有
        List<User> findlist = userService.Servicefindlist();

         //通过反射获取所有公开属性
        Field[] declaredFields = User.class.getDeclaredFields();

        //创建一个declaredFields长度的数组
        String[] titles = new String[declaredFields.length];
        String[] attribute = new String[declaredFields.length];
        for (int i = 0; i <declaredFields.length-1; i++) {
            //代表属性对象
            Field declaredField = declaredFields[i];
            //获取指定的注解
            AnnName annotation = declaredField.getAnnotation(AnnName.class);
            Content content = declaredField.getAnnotation(Content.class);
            String value = content.value();
            String name = annotation.name();
            attribute[i] = value;
            titles[i] = name;
        }

        ziMap.put("id",attribute);
        ziMap.put("text",titles);

        zilist.add(ziMap);
        for (User user : findlist) {
            map.put("id",user.getId());
            map.put("text",user.getName());
            map.put("iconCls","icon-save");
            map.put("state","open");
            for (Map.Entry<String, Object> entry : ziMap.entrySet()) {
                map.put("children",zilist);
            }

        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry);
        }
        lists.add(map);


        return lists;
    }

    //查用户注册信息
    @RequestMapping("/findtime.do")
    public Map<String, Map<String, Object>> findtime(){
        System.out.println("进入用户注册信息查询...");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(userService.Servicefindtime(7));
        list.add(userService.Servicefindtime(14));
        list.add(userService.Servicefindtime(21));
        list.add(userService.Servicefindtime(60));
        list.add(userService.Servicefindtime(120));
        list.add(userService.Servicefindtime(240));
        list.add(userService.Servicefindtime(356));
        HashMap<String, Map<String, Object>> map = new HashMap<>();
        Map<String, Object> hashMap = new HashMap<>();

        hashMap.put("data",list);
        map.put("counts",hashMap);

       return map;
    }

    //查询地区用户性别
    @RequestMapping("/findsex.do")
    public Map<String,Object> findsex(){
        System.out.println("123来了吗？");
        HashMap<String, Object> map = new HashMap<>();
        List<Usersite> man = userService.Servicefindman();
        List<Usersite> woman = userService.Servicefindwoman();

        map.put("man",man);
        map.put("woman",woman);
        return map;
    }

}
