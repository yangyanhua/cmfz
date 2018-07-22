package com.xuxinhui.dao;

import com.xuxinhui.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {
    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    Admin selectAdmin(@Param("name")String name, @Param("password")String password);

}
