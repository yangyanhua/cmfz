package com.baizhi.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.util.FileBrowserUtil;

@Controller
@RequestMapping("/imgs")
public class ImageBrowserController {

	
	//浏览文件
	@RequestMapping("/browser")
	public void browser(String path, HttpServletRequest request,HttpServletResponse response){
		try {
			String jsons=FileBrowserUtil.getFiles(request.getSession().getServletContext().getRealPath("/upload"+path),request.getContextPath(), request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(jsons);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 上传文件
	 * @param image
	 * @param request
	 * @param response
	 */
	@RequestMapping("/uploadImg")
	public void upload(MultipartFile image,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String, String> message=new HashMap<String, String>();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateDir=simpleDateFormat.format(new Date());
			String path=request.getSession().getServletContext().getRealPath("/upload")+"/"+dateDir;
			File fileDir=new File(path);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			String originalFilename = UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(image.getOriginalFilename());
			image.transferTo(new File(path,originalFilename));
			message.put("type", "success");
			String url= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
			map.put("url",url+"/upload/"+dateDir+File.separator+originalFilename);
		} catch (IOException e) {
			message.put("type", "error");
			e.printStackTrace();
		}
		map.put("message", message);
		try {
			String jsons=JSONObject.toJSONString(map);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(jsons);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
