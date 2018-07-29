package com.yangyh.service;

import com.yangyh.annotation.LogAnntation;
import com.yangyh.annotation.Logging;
import com.yangyh.dao.JournalDao;
import com.yangyh.entity.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
/*崔衫哎可生咯*/
@Transactional(propagation = Propagation.REQUIRED)
public class JournalServiceImpl implements JournalService {
    @Autowired
    private JournalDao journalDao;

    //UUID随机生成纯数字
    public static String getOrderNo(){
        String orderNo = "" ;
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        orderNo = trandNo.toString().substring(0, 4);
        orderNo = orderNo + sdf ; return orderNo.substring(0,16) ;
    }


    @Logging(value = "日志的分页",parameters = {@LogAnntation("起始下标"),@LogAnntation("显示条数")})
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Journal> ServicePaging(Integer page, Integer rows) {
        return journalDao.Paging((page-1)*rows,rows);
    }

    @Logging(value = "查询日志所有条数")
    @Override
    public Integer ServicetotalCounts() {
        return journalDao.totalCounts();
    }

  //  @Logging(value = "添加",parameters = {@LogAnntation("根据对象添加")})
    @Override
    public void Serviceadd(Journal journal) {
        journal.setId(getOrderNo());
        journalDao.add(journal);
    }

   @Logging(value = "删除日志",parameters = {@LogAnntation("根据对象删除")})
    @Override
    public void Servicedelete(Journal journal) {

        journalDao.delete(journal);
    }

    @Logging(value = "批量删除日志",parameters = {@LogAnntation("根据ID删除")})
    @Override
    public void ServiceBatchdeletion(List<Integer> list) {
        journalDao.Batchdeletion(list);
    }
}
