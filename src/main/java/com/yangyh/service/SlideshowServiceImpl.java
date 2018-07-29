package com.yangyh.service;

import com.yangyh.annotation.LogAnntation;
import com.yangyh.annotation.Logging;
import com.yangyh.dao.SlideshowDao;
import com.yangyh.entity.Slideshow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SlideshowServiceImpl implements SlideshowService {
    @Autowired
    private SlideshowDao slideshowDao;

    @Logging(value = "查所有轮播图")
    @Override
    public List<Slideshow> servicequerylist() {
        List<Slideshow> servicelist = slideshowDao.servicelist();
        return servicelist;
    }


    //UUID随机生成纯数字
    public static String getOrderNo(){
        String orderNo = "" ;
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        orderNo = trandNo.toString().substring(0, 4);
        orderNo = orderNo + sdf ; return orderNo.substring(0,16) ;
    }


    @Logging(value = "添加轮播图",parameters = {@LogAnntation("根据对象添加")})
    @Override
    public void serviceaddSlideshow(Slideshow slideshow) {
        slideshow.setId(getOrderNo());
        slideshowDao.serviceadd(slideshow);
    }

    @Logging(value ="查一个轮播图",parameters = {@LogAnntation("根据对象ID查一个")})
    @Override
    public Slideshow servicefind(Slideshow slideshow) {
        Slideshow servicefind = slideshowDao.servicefind(slideshow);
        return servicefind;
    }

    @Logging(value = "删除轮播图",parameters = {@LogAnntation("根据对象删除")})
    @Override
    public void serviceremoveSlideshow(Slideshow slideshow) {
        System.out.println(slideshow);
        slideshowDao.servicedelete(slideshow);
    }

    @Logging(value = "修改轮播图")
    @Override
    public void serviceupdate(Slideshow slideshow) {
        slideshowDao.serviceupdate(slideshow);
    }

    @Logging(value = "轮播图分页",parameters = {@LogAnntation("起始下标"),@LogAnntation("显示条数")})
    @Override
    public List<Slideshow> servicePaging(Integer page, Integer rows) {
        List<Slideshow> paging = slideshowDao.Paging((page-1)*rows, rows);
        return paging;
    }

    @Logging(value = "轮播图查询所有条数")
    @Override
    public Integer servicetotalCounts() {
        Integer integer = slideshowDao.totalCounts();
        return integer;
    }
}
