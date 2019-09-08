package com.sz.service.impl;

import com.sz.mapper.AdminMapper;
import com.sz.pojo.Admin;
import com.sz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdmin(Admin admin) {

        return adminMapper.selectAdmin(admin);
    }
}
