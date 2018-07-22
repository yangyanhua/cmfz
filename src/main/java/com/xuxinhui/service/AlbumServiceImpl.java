package com.xuxinhui.service;

import com.xuxinhui.annotation.LogAnntation;
import com.xuxinhui.annotation.Logging;
import com.xuxinhui.dao.AlbumDao;
import com.xuxinhui.dao.MusicDao;
import com.xuxinhui.entity.Album;
import com.xuxinhui.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private MusicDao musicDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Logging(value = "查询所有专辑")
    @Override
    public List<Album> Servicefindlist() {
        List<Album> findlist = albumDao.findlist();
        return findlist;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Logging(value = "专辑分页",parameters = {@LogAnntation("起始下标"),@LogAnntation("显示条数")})
    @Override
    public List<Album> ServicePaging(Integer page, Integer rows) {
        List<Album> paging = albumDao.Paging((page - 1) * rows, rows);
        return paging;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Logging(value = "查询专辑所有条数")
    @Override
    public Integer ServicetotalCounts() {
        return albumDao.totalCounts();
    }

    //UUID随机生成纯数字
    public static String getOrderNo(){
        String orderNo = "" ;
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        orderNo = trandNo.toString().substring(0, 4);
        orderNo = orderNo + sdf ; return orderNo.substring(0,16) ;
    }


    @Logging(value = "添加专辑",parameters = {@LogAnntation("根据对象添加")})
    @Override
    public void Serviceadd(Album album) {

        album.setId(getOrderNo());

        Music music = new Music();
        music.setId(getOrderNo());
        music.setAid(album.getId());
        musicDao.add(music);

         albumDao.add(album);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Logging(value = "查一个专辑",parameters = {@LogAnntation("ID")})
    @Override
    public Album Servicefind(String id) {
        return albumDao.find(id);
    }

    @Override
    public List<Album> Servicefindwuge() {
        return albumDao.findwuge();
    }
}
