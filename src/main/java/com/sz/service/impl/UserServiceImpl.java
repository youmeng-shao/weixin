package com.sz.service.impl;

import com.sz.mapper.UserMapper;
import com.sz.pojo.Counts;
import com.sz.pojo.Menu;
import com.sz.pojo.User;
import com.sz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    // 注入mapper
    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl() {
        System.out.println("service创建");
    }


    public Integer saveQueueUser(User user) {

        // 用户付完钱，插入user表中
        userMapper.insertQueueUser(user);

        // 需要把用户点的饭菜和数量插入到user_menu表中

        for (int i=0; i<user.getMenus().size(); ++i) {
            Menu tempMenu = user.getMenus().get(i);
            Counts counts = user.getCount().get(i);
            userMapper.insertUserWithMenus(user.getOpenId(),
                    tempMenu.getMenuId(), counts.getNum());
        }
        return 1;
    }


    public List<User> findAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public Integer deleteUserMenuByopenId(String openId) {
        return userMapper.deleteUserAndMenuByopenId(openId);
    }

    @Override
    public Integer findTableIdByopenId(String openId) {

        return userMapper.selectTableIdByopenId(openId);
    }

    @Override
    public Integer deleteUserByopenId(String openId) {

        return userMapper.deleteUserByopenId(openId);
    }

}
