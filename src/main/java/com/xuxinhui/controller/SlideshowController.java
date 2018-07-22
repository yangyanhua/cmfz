package com.xuxinhui.controller;

import com.xuxinhui.entity.Slideshow;
import com.xuxinhui.service.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/show")
public class SlideshowController {
    @Autowired
    private SlideshowService slideshowService;

    @RequestMapping("/find.do")
    @ResponseBody
    public Map<String,Object> find(Integer page,Integer rows){
        //总条数
        Integer integer = slideshowService.servicetotalCounts();
        //分页
        List<Slideshow> list = slideshowService.servicePaging(page, rows);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total", integer);
        map.put("rows", list);
        return map;
    }

    //删除
    @RequestMapping("/operation.do")
    @ResponseBody
    public String operation(Slideshow slideshow){
        System.out.println("删除方法");
        Slideshow findid = slideshowService.servicefind(slideshow);
        System.out.println(findid);

        if(findid.getState().equals("yes")){
            System.out.println("进入yes");
            slideshow.setState("no");
            slideshowService.serviceupdate(slideshow);
            return "ok";
        }else{
            System.out.println("进入no");
            slideshowService.serviceremoveSlideshow(slideshow);
            return "ok";
        }

    }
    //添加
    @RequestMapping("/add.do")
    @ResponseBody
    public String add(Slideshow slideshow){
        System.out.println("进入方法");
        slideshowService.serviceaddSlideshow(slideshow);
        return "ok";
    }

    //文件上传
    @RequestMapping("/upload.do")
    @ResponseBody
    public String upfile(MultipartFile url, HttpSession session) throws Exception{
        System.out.println("来了吗？？？");
        System.out.println("这是图片"+url);
        String filename = url.getOriginalFilename();//文件名
        String contentType = url.getContentType();//文件类型
        System.out.println("这是文件名"+filename);
        System.out.println("这是文件类型"+contentType);

      String realPath = session.getServletContext().getRealPath("/upload");//获取真实路径
        url.transferTo(new File(realPath+"/"+filename));//将文件放到指定目录
        System.out.println("../upload/"+filename);
        System.out.println("这是目录"+realPath);
        /*Map<String,String> map = new HashMap<String,String>();*/
        /*map.put("url","../upload/"+filename);*/
        return "/upload/"+filename;
    }

    //修改状态
    @RequestMapping("/updatesta.do")
    @ResponseBody
    public String updatesta(Slideshow slideshow){
        System.out.println("来了吗？？？");
        System.out.println(slideshow);
        slideshow.setAltertime(new Date());
        slideshowService.serviceupdate(slideshow);
        return "ok";
    }
}

