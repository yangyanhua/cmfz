package com.yangyh.poi;

import com.yangyh.annotation.AnnName;
import com.yangyh.entity.Slideshow;
import com.yangyh.service.SlideshowService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class ExportPOI {
    @Autowired
    private SlideshowService slideshowService;
    //导入
    public static void main(String[] args) throws IOException {
        // 创建excel
        Workbook work = new HSSFWorkbook();
        //创建excel工作文件名字
        Sheet sheet = work.createSheet("第一个工资表");
        //在sheet工资表：创建行，且开始下标从0开始
        Row row = sheet.createRow(0);
        //在row行创建单元格下标以0开始
        Cell cell = row.createCell(0);
        //给行注入值
       // cell.setCellValue("不好啊啊啊啊啊啊");
        cell.setCellValue(new Date());  //注入时间
        DataFormat dataFormat = work.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日HH时mm分ss秒");//设置时间类型

        //设置单元格的文字居中,前提要给work创建一个特殊对象
        CellStyle cellStyle = work.createCellStyle();
        //在cellStyle里创建对齐方式
        cellStyle.setAlignment(HorizontalAlignment.CENTER );
             cellStyle.setDataFormat(format);//指定日期格式
        //创建文字颜色对象
        Font font = work.createFont();
        font.setColor(Font.COLOR_RED);
        font.setBold(true);
        //把颜色放到cellStyle
        cellStyle.setFont(font);
        //设置列的宽度,第一个参数为要设置的下标,第二个为设置的宽度
        sheet.setColumnWidth(0,30*256);

        //指定给那个单元格设置居中,把设置单元格位置对象放进来
        cell.setCellStyle(cellStyle);

        //指定创建excel放的位置
        ((HSSFWorkbook) work).write(new File("i:ccc.xls"));
    }
    //导入
    @Test
    public void  test1() throws IOException {
        HSSFWorkbook work = new HSSFWorkbook();
        //创建工作表
        HSSFSheet usersheet = work.createSheet("用户信息");
        //填写标题行数据
        HSSFRow row = usersheet.createRow(0);

        //怎么获取属性对应的中文？
                                                //获取所有的公开属性
        Field[] fieldsFields = Slideshow.class.getDeclaredFields();
        //创建一个fieldsFields长度的数组
        String[] titles=new String[fieldsFields.length];

        for (int i = 0; i < fieldsFields.length; i++) {
            //代表属性对象
            Field fieldsField = fieldsFields[i];
            //拿到指定注解
            AnnName annotation = fieldsField.getAnnotation(AnnName.class);
            String name = annotation.name();
            titles[i]=name;
        }
        
        usersheet.setColumnWidth(4,30*256);
        usersheet.setColumnWidth(5,30*256);

        for (int i = 0; i < titles.length; i++) {

            String title = titles[i];
            HSSFCell cell = row.createCell(i);
             cell.setCellValue(title);
        }

        DataFormat dataFormat = work.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日HH时mm分ss秒");//设置时间类型
        HSSFCellStyle cellSty = work.createCellStyle();
        cellSty.setDataFormat(format);

        //填写用户数据
        List<Slideshow> list = slideshowService.servicequerylist();

        for (int i = 1; i <=list.size(); i++) {
            String title = titles[i];
            //创建行
            HSSFRow hang = usersheet.createRow(i);
            hang.createCell(0).setCellValue(list.get(i-1).getId());
            hang.createCell(1).setCellValue(list.get(i-1).getDescribe());
            hang.createCell(2).setCellValue(list.get(i-1).getUrl());
            hang.createCell(3).setCellValue(list.get(i-1).getState());
            hang.createCell(4).setCellValue(list.get(i-1).getTime());
            hang.createCell(5).setCellValue(list.get(i-1).getAltertime());
}
            work.write(new File("i:/用户表.xls"));
    }
    //导出
    @Test
    public void testw() throws IOException, ParseException {
        HSSFWorkbook work = new HSSFWorkbook(new FileInputStream(new File("i:/用户表.xls")));
        //获取工作表
        HSSFSheet sheet = work.getSheet("用户信息");
        //从数据行开始读取
        ArrayList<Slideshow> list = new ArrayList<>();
        for (int i = 1; i <=sheet.getLastRowNum(); i++) {
            //读取行
            HSSFRow row = sheet.getRow(i);
            //读取每一行数据,封装成user对象
            Slideshow sli = new Slideshow();
            sli.setId(row.getCell(0).getStringCellValue());
            sli.setDescribe(row.getCell(1).getStringCellValue());
            sli.setUrl(row.getCell(2).getStringCellValue());
            sli.setState(row.getCell(3).getStringCellValue());
            //Date类型转String类型
           // SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
            Date parse = (row.getCell(4).getDateCellValue());
            sli.setTime(parse);
            Date date = row.getCell(5).getDateCellValue();
            sli.setAltertime(date);
            list.add(sli);


        }
        //入库操作
        for (Slideshow slideshow : list) {
            System.out.println(slideshow);
        }
    }
}



