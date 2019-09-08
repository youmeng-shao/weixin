package com.sz.mapper;

import com.sz.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 用户支付之后，把信息插入到数据库中
     *
     * @Param User 对象
     * @return Integer
     * */
    Integer insertQueueUser(User user);

    /**
     * 把用户点的饭菜和数量插入到中间表中
     * @Param openId 用户openId
     * @Param menuId 饭菜的menuId
     * @Param count menuId饭菜的数量
     * @return Integer
     *
     * */
    Integer insertUserWithMenus(@Param("openId") String openId,
                                @Param("menuId") Integer menuId,
                                @Param("num") Integer num);

    /**
     * 返回在排队中的所有用户
     * */
    List<User> selectAllUser();

    /**
     * 可以的菜已经上齐，删除user_menu表中openId
     * @Param String openId
     * @return Integer
     * */
    Integer deleteUserAndMenuByopenId(@Param("openId") String openId);

    /**
     * 查找用户为openid, 的tableId, 返回值为tableId
     * @param openId
     * @return tableId
     * */
    Integer selectTableIdByopenId(@Param("openId") String openId);

    /**
     * 删除用户为openid的用户，在user表中
     * @param openId
     * @return Integer
     * */
    Integer deleteUserByopenId(@Param("openId") String openId);
}
