package com.yangyh.interfaceController;

import com.yangyh.entity.Slideshow;
import com.yangyh.service.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lunbotu")
public class lunbotuController {
    @Autowired
    private SlideshowService slideshowService;


    @RequestMapping("/findtu.do")
    public Map<String, Object> findtu(String type){
        System.out.println("来了吗？？？");
        HashMap<String, Object> map = new HashMap<>();
        if(type=="all"){
            List<Slideshow> list = slideshowService.servicequerylist();
            for (Slideshow slideshow : list) {
                map.put("thumbnail",slideshow.getUrl());
                map.put("desc",slideshow.getDescribe());
                map.put("id",slideshow.getId());
            }
        }


        return map;
    }
}

