package com.yangyh.controller;

import com.yangyh.entity.Guru;
import com.yangyh.entity.User;
import com.yangyh.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RequestMapping("/poi")
@Controller
public class POIController {
    @Autowired
    private UserService userService;
    @RequestMapping("/export.do")
    public void export(String content, String fields, HttpServletResponse response) throws IOException {
        System.out.println("这是content:"+content);
        System.out.println("这是fields:"+fields);
        //导出Excel
        HSSFWorkbook work = new HSSFWorkbook();
        //创建工作excel库
        HSSFSheet usersheet = work.createSheet("用户表");

        //建立标题行
        HSSFRow titleRow = usersheet.createRow(0);
                                //拆分数组以逗号的形式才分
        String[] split = content.split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            //创建标题 有几个属性就创建几个
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(s);
        }
        //创建数据行
        //查询数据库所有的用户信息
        List<User> list = userService.Servicefindlist();

        //以逗号形式拆分fields
        String[] strings = fields.split(",");

        for (int i = 1; i <=list.size(); i++) {
            //创建数据行
            HSSFRow datarow = usersheet.createRow(i);

            for (int j = 0; j < strings.length; j++) {
               //创建单元格
                HSSFCell cell = datarow.createCell(j);
                //通过拼凑方法名,调用反射的形式获取属性值
                String Myname = "get"+strings[j].substring(0,1).toUpperCase()+strings[j].substring(1);
                //方法名已经拿到,在拿方法对象

                    try {
                        Method declaredMethod = User.class.getDeclaredMethod(Myname, null);
                        System.out.println("这是declaredMethod"+declaredMethod);
                        //拿到结果
                        System.out.println("这是list|i-1:"+list.get(i-1)+"这是次数："+i);

                        Object result = declaredMethod.invoke(list.get(i-1));

                        System.out.println("这是try中的+result："+result+"这是次数："+i);
                        //判断返回值的类型
                        if(result instanceof Date){
                            //设置宽度
                           usersheet.setColumnWidth(j,40*256);
                           //设置日期格式
                           DataFormat dataFormat = work.createDataFormat();
                            short format = dataFormat.getFormat("yyyy年MM月dd日");

                            CellStyle cellStyle = work.createCellStyle();
                            //居中
                            cellStyle.setAlignment(HorizontalAlignment.CENTER);
                            cellStyle.setDataFormat(format);
                            cell.setCellStyle(cellStyle);
                            //设置内容  如果是时间内容就强转
                            cell.setCellValue((Date)result);
                        }else {
                            cell.setCellValue(result.toString());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }

            }

        }
        //设置响应头
        response.setContentType("application/vnd.ms-excel");
        String fileName="用户自定义信息表.xls";
        //当前行为方式   告诉浏览器是下载 还是 打开新窗口临时加载
        response.setHeader("content-disposition","attachment;fileName="+new String(fileName.getBytes("utf-8"),"iso-8859-1"));

        //写出excle
       work.write(response.getOutputStream());
        System.out.println("走完了吗？");
    }

    @RequestMapping("/daoru.do")
    public void InAllUser(MultipartFile file){

        int result = 0;
        try {
            String originalFilename = file.getOriginalFilename();
            String path = "D:/XXHIdea/WorkSpace/cmfz/src/main/webapp/mp3";
            String ppath = new Date().getTime()+originalFilename;
            file.transferTo(new File(ppath));

            //获取文件数据流
            FileInputStream inputStream = new FileInputStream(ppath);

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

            HSSFSheet sheet = workbook.getSheet("用户信息");

            int lastRowNum = sheet.getLastRowNum();

            //定义一个USer的list来接收数值
            List<User> ulist = new ArrayList<User>();

            for (int i = 1; i <= lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                User user = new User();
                user.setId(UUID.fromString("1").toString().substring(1));
                user.setName(row.getCell(1).getStringCellValue());
                user.setFhname(row.getCell(2).getStringCellValue());
                user.setEmail(row.getCell(3).getStringCellValue());
                user.setPhone(row.getCell(4).getStringCellValue());
                user.setPassword(row.getCell(5).getStringCellValue());
                user.setSex(row.getCell(6).getStringCellValue());
                user.setSite(row.getCell(7).getStringCellValue());
                user.setHeadurl(row.getCell(8).getStringCellValue());
                user.setSignature(row.getCell(9).getStringCellValue());
                user.setState(row.getCell(10).getStringCellValue());
                user.setLogintime(row.getCell(11).getDateCellValue());
                user.setAddtime(row.getCell(12).getDateCellValue());
                user.setQq(row.getCell(13).getStringCellValue());
                user.setWeix(row.getCell(14).getStringCellValue());
                Guru teacher = new Guru();
                teacher.setId(row.getCell(15).getStringCellValue());
                user.setGuru(teacher);
                //维护关系属性
                ulist.add(user);
            }
            System.out.println(ulist);
            result = userService.ServiceinserAll(ulist);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
