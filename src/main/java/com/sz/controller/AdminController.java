package com.sz.controller;

import com.sz.pojo.Admin;
import com.sz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login")
    public String checkLogin(Admin admin, HttpSession session) {
        Admin tempAdmin = adminService.getAdmin(admin);
        if (tempAdmin==null) {

            return "redirect:/login.jsp";
        } else {
            session.setAttribute("SESSION_ADMIN", admin);
            return "home";
        }
    }

}
