package com.sz.mapper;

import com.sz.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    /**
     * 新增菜品
     * @Param Menu对象
     * @return
     * */
    int insertMenu(Menu menu);

    /**
     * 根据id删除菜品
     * @Param menuId
     * @return
     * */
    int deleteMenuById(Integer menuId);
    /**
     * 根据id查找菜品
     * @Param menuId
     * @return 返回查找到的Menu对象
     * */
    Menu selectMenuById(Integer menuId);

    /**
     * 更新menu
     * @Patam Menu对象
     * @return
     * */
    int updataMenu(Menu menu);

    /**
     * 根据菜名查找
     * @Param String menuName
     * @return List 里面的对象是Menu
     * */
    List<Menu> selectMenuByName(@Param("menuName") String menuName,
                                @Param("startLine")Integer startLine,
                                @Param("pageCount")Integer pageCount);

    /**
     * 实现分页查找
     * */
    List<Menu> selectMenuPages(@Param("startLine")Integer startLine,
                               @Param("pageCount")Integer pageCount);

    /**
     * 查找出里面有多少条数据
     *
     * */
    int selectMenuAll();

    /**
     * 模糊查询有多少条数据
     * */
    int selectAllMenuByName(@Param("menuName") String menuName);


    /**************微信小程序需要的功能************/

    /**
     * 返回已经上架的所有菜品
     * @return List 里面的元素是menu对象
     *
     * */
    List<Menu> selectAllMenuByStatue();

}
