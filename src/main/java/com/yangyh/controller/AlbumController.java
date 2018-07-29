package com.yangyh.controller;

import com.yangyh.entity.Album;
import com.yangyh.entity.Music;
import com.yangyh.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    /**
     * 分页
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/Paging.do")
    public Map<String,Object> Paging(Integer page, Integer rows){
        Integer integer = albumService.ServicetotalCounts();
        List<Album> albums = albumService.ServicePaging(page, rows);

        HashMap<String, Object> map = new HashMap<>();
        map.put("total", integer);
        map.put("rows", albums);
        return map;
    }

    /**
     * 添加
     * @param album
     * @return
     */
    @RequestMapping("/add.do")
    public String add(Album album){
        System.out.println("来了吗？");
        System.out.println(album);
        albumService.Serviceadd(album);
        return "ok";
    }

    @RequestMapping("/music.do")
    public List<Music> music(String id){
        System.out.println("进入音乐方法了吗？");
        Album album = albumService.Servicefind(id);
        return album.getMusic();
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


}

