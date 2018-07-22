package com.xuxinhui.controller;

import com.xuxinhui.entity.Album;
import com.xuxinhui.entity.Essay;
import com.xuxinhui.entity.Slideshow;
import com.xuxinhui.service.AlbumService;
import com.xuxinhui.service.EssayService;
import com.xuxinhui.service.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lunbotu")
public class lunbotuController {
    @Autowired
    private SlideshowService slideshowService;
    @Autowired
    private EssayService essayService;
    @Autowired
    private AlbumService albumService;
    @RequestMapping("/findtu.do")
    public Map<String,List<Object>> findtu(String type){

        HashMap<String, List<Object>> hashMap = new HashMap<>();

        ArrayList<Object> objects = new ArrayList<>();
        ArrayList<Object> object1 = new ArrayList<>();
        ArrayList<Object> object2 = new ArrayList<>();
        if("all".equals(type)){

            List<Slideshow> list = slideshowService.servicequerylist();
            for (Slideshow slideshow : list) {
                HashMap<String, Object> map = new HashMap<>();
                System.out.println(slideshow);
                map.put("thumbnail",slideshow.getUrl());
                map.put("desc",slideshow.getDescribe());
                map.put("id",slideshow.getId());
                objects.add(map);
            }
            List<Essay> essays = essayService.servicEssaylist();

           for (Essay essay : essays) {
                HashMap<String, Object> map1 = new HashMap<>();
                map1.put("thumbnail",essay.getGurn().getUrl());
                map1.put("title",essay.getTitle());
                map1.put("author",essay.getGurn().getUpname());
                map1.put("type",1);
                map1.put("set_count",essay.getContent());
                map1.put("create_date",essay.getTime());
                object1.add(map1);
            }
            List<Album> servicefindlist = albumService.Servicefindwuge();
            for (Album album : servicefindlist) {
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("thumbnail",album.getAlbumurl());
                map2.put("title",album.getAlbumname());
                map2.put("author",album.getAlbuintro());
                map2.put("type",0);
                map2.put("set_count",album.getPending());
                map2.put("create_date",album.getAddalbumtime());
                object2.add(map2);
            }

        }
        hashMap.put("header",objects);
        hashMap.put("body1111111111",object1);
        hashMap.put("body",object2);
        return hashMap;
    }

    @RequestMapping("/wen.do")
    public Map<String,Object> wen(String id){
        Album album = albumService.Servicefind(id);
        System.out.println("来了吗？？？++++++");
        System.out.println(album.getMusic());
        List<Object> list = new ArrayList<>();

        List<Object> haslist = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        haslist.add("introduction");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("thumbnail",album.getAlbumurl());
        hashMap.put("title",album.getAlbumname());
        hashMap.put("score",album.getStars());
        hashMap.put("author",album.getAuthor());
        hashMap.put("broadcast",album.getTeller());
        hashMap.put("set_count",album.getPending());
        hashMap.put("brief",album.getAlbuintro());
        hashMap.put("create_date",album.getIssuealbutime());
        list.add(hashMap);
        //音频
        hashMap.put("title",album.getMusic());

        map.put("introduction",list);

        //map.put("list");
        return  map;
    }
}

