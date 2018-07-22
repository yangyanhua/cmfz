package com.xuxinhui.service;

import com.xuxinhui.entity.Admin;

public interface AdminService {
    Admin  queryAdmin(String name,String password);
}
