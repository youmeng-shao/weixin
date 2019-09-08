package com.sz.service;

import com.sz.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 用户支付之后，把信息插入到数据库中
     *
     * @Param User 对象
     * @return Integer
     * */
    Integer saveQueueUser(User user);

    /**
     * 返回在排队中的所有用户
     * */
    List<User> findAllUser();

    /**
     * 可以的菜已经上齐，删除user_menu表中openId
     * @Param String openId
     * @return Integer
     * */
    Integer deleteUserMenuByopenId(String openId);


    /**
     * 查找用户为openid, 的tableId, 返回值为tableId
     * @param openId
     * @return tableId
     * */
    Integer findTableIdByopenId(String openId);

    /**
     * 删除用户为openid的用户，在user表中
     * @param openId
     * @return Integer
     * */
    Integer deleteUserByopenId(String openId);


}
