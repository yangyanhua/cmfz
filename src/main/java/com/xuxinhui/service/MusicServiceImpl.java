package com.xuxinhui.service;

import com.xuxinhui.annotation.LogAnntation;
import com.xuxinhui.annotation.Logging;
import com.xuxinhui.dao.MusicDao;
import com.xuxinhui.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicDao musicDao;

    @Logging(value = "添加音乐",parameters = {@LogAnntation("根据对象添加")})
    @Override
    public void Serviceadd(Music music) {
        musicDao.add(music);
    }
}
