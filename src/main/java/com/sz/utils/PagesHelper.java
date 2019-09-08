package com.sz.utils;

import com.sz.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class PagesHelper {

    public static Integer PAGES_COUNTS = 4;

    public static int getPagesCount(Integer allDate) {
        int count=0;
        if (allDate%PAGES_COUNTS!=0) {
            count = allDate/PAGES_COUNTS+1;
        } else {
            count = allDate/PAGES_COUNTS;
        }
        return count;
    }

    // 返回需要在前端展示的queueUser信息
    public static List<User> getViewUsers(List<User> allUsers, Integer currentPage) {
        List<User> viewUsers = new ArrayList<>();
        for (int i=(currentPage-1)*PAGES_COUNTS;
             i<allUsers.size()&&i<currentPage*PAGES_COUNTS; ++i) {
            viewUsers.add(allUsers.get(i));
        }
        return  viewUsers;
    }

}
