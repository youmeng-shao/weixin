package com.sz.service;

import com.sz.pojo.Menu;

import java.util.List;

public interface MenuService {
    /**
     * 新增菜品
     * @Param Menu对象
     * @return
     * */
    int addMenu(Menu menu);

    /**
     * 根据id删除菜品
     * @Param menuId
     * @return
     * */
    int delteMenuById(Integer menuId);

    /**
     * 根据id查找菜品
     * @Param menuId
     * @return 返回查找到的Menu对象
     * */
    Menu findMenuById(Integer menuId);

    /**
     * 更新menu
     * @Patam Menu对象
     * @return
     * */
    int editMenu(Menu menu);

    /**
     * 查找出里面有多少条数据
     *
     * */
    int findMenuAll();

    /**
     * 实现分页查找
     * */
    List<Menu> findMenuPages(Integer startLine, Integer pageCount);

    /**
     * 根据菜名进行模糊查找
     * */
    List<Menu> findMenuByName(String menuName, Integer startLine, Integer pageCount);

    /**
     * 模糊查询有多少条数据
     * */
    int findAllMenuByName(String menuName);

    /**
     * 返回已经上架的所有菜品
     * @return List 里面的元素是menu对象
     *
     * */
    List<Menu> findAllMenuByStatue();


}
