package com.baizhi.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 浏览文件的工具类
 */
public class FileBrowserUtil{
	/**
	 * 获取所有文件
	 * @param path
	 * @param basePath
	 * @param uri
	 * @return
	 */
	public static String getFiles(String path,String basePath,String uri){//获取图片路径
		File dirFile=new File(path);
		File[] listFiles = dirFile.listFiles();
		List<Map<String,Object>> files=new ArrayList<Map<String,Object>>();
		for (File file : listFiles) {
			Map<String,Object> map=new HashMap<String, Object>();
			if(file.isDirectory()){
				map.put("isDirectory", true);
				map.put("name", file.getName());
			}else{
				map.put("isDirectory", false);
				map.put("name", file.getName());
				map.put("url",file.getAbsolutePath());
				map.put("size",file.length());
				map.put("lastModified",file.lastModified());
				String url=basePath+getParentPath(file,basePath.substring(basePath.indexOf("/")+1)).replace("\\", "/");
				map.put("url", uri+url);
			}
			files.add(map);
		}
		return JSONObject.toJSONStringWithDateFormat(files,"yyyy-MM-dd");
	}
	//递归获取文件
	private static String getParentPath(File file,String appName){
		if(appName.equals(file.getName())){
			return "";
		}else{
			return getParentPath(file.getParentFile(),appName)+File.separator+file.getName();
		}
	}
}
