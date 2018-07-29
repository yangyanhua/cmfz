package com.yangyh.controller;

import com.alibaba.fastjson.JSONObject;
import com.yangyh.entity.Essay;
import com.yangyh.entity.Guru;
import com.yangyh.service.EssayService;
import com.yangyh.service.GurnService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/editor")
public class EssayController {
    @Autowired
    private EssayService essayService;
    @Autowired
    private GurnService gurnService;
    //上传图片
    @RequestMapping("/add.do")
    public JSONObject findtup(MultipartFile file, HttpServletRequest request) throws UnknownHostException {
        System.out.println("来啦吗？？");

        JSONObject obj=new JSONObject();

      //图片路径
        String filename = file.getOriginalFilename(); //文件名
        System.out.println("这是文件名"+filename);
        String ext = FilenameUtils.getExtension(filename);//拿到后缀名
        System.out.println("这是后缀名"+ext);
        //以UUID格式创建一个新的文件名加上扩展名.
        String newFilename = UUID.randomUUID().toString() + "." + ext;

        String scheme = request.getScheme();//协议名 http

        //  获取当前服务站IP地址     一般id地址不会用数据库的 会用当前的
        String localHost = InetAddress.getLocalHost().getHostAddress();

        String serverName = request.getServerName();//服务器名 locahost
        int serverPort = request.getServerPort();//端口 8989
        String contextPath = request.getContextPath();//项目名
        //实际服务器路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload");

        File img = new File(realPath + "/" + newFilename);

        //拼凑图片网络位置
       String neraddr=scheme+"://"+localHost+":"+serverPort+contextPath+"/upload/"+newFilename;

        try {
            //文件上传
             file.transferTo(img);
            obj.put("error",0);
            obj.put("url",neraddr);


        } catch (Exception e) {
            obj.put("error",1);
            obj.put("url",neraddr);
        }
        return obj;
    }
    //游览服务器上的所有图片
    @RequestMapping("/findtuo.do")
    public JSONObject findtuo(HttpServletRequest request) throws UnknownHostException {
        System.out.println("来了吗？游览全部图片");
        JSONObject obj = new JSONObject();

        String scheme = request.getScheme();//协议名 http

        //  获取当前服务站IP地址     一般id地址不会用数据库的 会用当前的
        String localHost = InetAddress.getLocalHost().getHostAddress();

        String serverName = request.getServerName();//服务器名 locahost
        int serverPort = request.getServerPort();//端口 8989
        String contextPath = request.getContextPath();//项目名
        //拼凑图片网络位置
        String neraddr=scheme+"://"+localHost+":"+serverPort+contextPath+"/upload/";
        obj.put("current_url",neraddr);
        //获取实际服务器路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        File file = new File(realPath);

        //获取当前文件夹里所有文件名
        String[] list = file.list();

        obj.put("total_count",list.length);
        ArrayList<Object> arlist = new ArrayList<>();


        for (int i=0;i<list.length;i++){
            String name=list[i];
            //往arlist中设置所有图片
            JSONObject js = new JSONObject();
            js.put("is_dir",false);
            js.put("has_file",false);
            File fl = new File(realPath+"/"+list[i]);
            js.put("filesize",fl.length());
            js.put("is_photo",true);
            js.put("filetype",FilenameUtils.getExtension(fl.getName()));
            js.put("filename",name);

            //自定义时间格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            String format = dateFormat.format(new Date());
            js.put("datatime",format);

            arlist.add(js);
        }


        obj.put("file_list",arlist);
      
        return obj;
    }


    @RequestMapping("/addessay.do")
    public String addessay(Essay essay){
        System.out.println("来了吗？");
        System.out.println("："+essay.getContent());

        essayService.add(essay);
        return "ok";
    }

    @RequestMapping("/findlist.do")
    public Map<String,Object> findlist(Integer page,Integer rows){
        //总条数
        Integer integer = essayService.servicetotalCounts();
        List<Essay> essays = essayService.servicePaging(page, rows);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", integer);
        map.put("rows", essays);
        return map;
    }
    @RequestMapping("/gurn.do")
    public List<Guru> gurn(HttpServletRequest request){
        System.out.println("来了吗？");
        List<Guru> findlList = gurnService.findlist();

        return findlList;
    }
}
