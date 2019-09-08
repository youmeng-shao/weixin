package com.sz.service;

import com.sz.pojo.Admin;

public interface AdminService {

    /**
     * 查找管理员的信息
     * @param admin (前端传来的管理员用户名和密码)
     * @return admin 根据信息查找数据，返回Admin对象
     *
     * */
    Admin getAdmin(Admin admin);
}
