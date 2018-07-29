package com.yangyh.controller;

import com.yangyh.entity.Admin;
import com.yangyh.service.AdminService;
import com.yangyh.util.SecurityCode;
import com.yangyh.util.SecurityImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/user")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 登录
     * @param name
     * @param password
     * @param session
     * @param code
     * @return
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public String login(String name,String password,HttpSession session,String code){
        String securityCode = (String) session.getAttribute("securityCode");
        System.out.println("来了啊？？？？");
        Admin admin = adminService.queryAdmin(name, password);

        if(code.equals(securityCode)){
            if(admin!=null){
               session.setAttribute("admin",admin);
                return "ok";
            }else{
                return "on";
            }
        }

        return  "on ";
    }

    /**
     * 获取验证码
     * @param session
     * @param response
     * @throws Exception
     */
    @RequestMapping("/code.do")
    public void code(HttpSession session, HttpServletResponse response) throws Exception {
        // 生成验证码随机数
        String securityCode = SecurityCode.getSecurityCode();
        // 将随机数存入session里
        session.setAttribute("securityCode", securityCode);
        // 生成验证码图片
        BufferedImage image = SecurityImage.createImage(securityCode);
        // 将验证码图片响应到客服端 //1.是图片 //2.这是格式 //3.这是响应
        ImageIO.write(image, "png", response.getOutputStream());

    }
    @RequestMapping("/empty.do")
    public void  empty(HttpSession session,HttpServletResponse response) throws IOException {
        System.out.println("进入退出系统...");
        session.invalidate();
        PrintWriter out = response.getWriter();
        out.print("ok");
        System.out.println("来了吗？？");
    }
}
