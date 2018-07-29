package com.yangyh.service;

import com.yangyh.annotation.LogAnntation;
import com.yangyh.annotation.Logging;
import com.yangyh.dao.EssayDao;
import com.yangyh.entity.Essay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EssayServiceImpl implements EssayService {
    @Autowired
    private EssayDao essayDao;


    @Override
    public List<Essay> servicEssaylist() {
        return essayDao.Essaylist();
    }

    @Logging(value = "文章删除",parameters = {@LogAnntation("根据对象删除")})
    @Override
    public void delete(Essay essay) {
    essayDao.servicedelete(essay);
    }

    //UUID随机生成纯数字
    public static String getOrderNo(){
        String orderNo = "" ;
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        orderNo = trandNo.toString().substring(0, 4);
        orderNo = orderNo + sdf ; return orderNo.substring(0,16) ;
    }

    @Logging(value = "文章添加",parameters = {@LogAnntation("根据对象添加")})
    @Override
    public void add(Essay essay) {
        essay.setId(getOrderNo());
    essayDao.serviceadd(essay);
    }

    @Logging(value = "文章分页",parameters = {@LogAnntation("起始下标"),@LogAnntation("显示条数")})
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Essay> servicePaging(Integer page, Integer rows) {
        List<Essay> paging = essayDao.Paging((page - 1) * rows, rows);
        return paging;
    }

    @Logging(value = "文章查询所有条数")
    @Override
    public Integer servicetotalCounts() {
        Integer integer = essayDao.totalCounts();
        return integer;
    }
}
