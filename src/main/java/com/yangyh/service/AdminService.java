package com.yangyh.service;

import com.yangyh.entity.Admin;

public interface AdminService {
    Admin  queryAdmin(String name,String password);
}
