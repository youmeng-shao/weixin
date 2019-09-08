package com.sz.service.impl;

import com.sz.mapper.MenuMapper;
import com.sz.pojo.Menu;
import com.sz.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int addMenu(Menu menu) {
        return menuMapper.insertMenu(menu);
    }

    @Override
    public int delteMenuById(Integer menuId) {
        return menuMapper.deleteMenuById(menuId);
    }

    @Override
    public Menu findMenuById(Integer menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    @Override
    public int editMenu(Menu menu) {
        return menuMapper.updataMenu(menu);
    }

    @Override
    public int findMenuAll() {
        return menuMapper.selectMenuAll();
    }

    @Override
    public List<Menu> findMenuPages(Integer startLine, Integer pageCount) {
        return menuMapper.selectMenuPages(startLine, pageCount);
    }

    @Override
    public List<Menu> findMenuByName(String menuName, Integer startLine, Integer pageCount) {
        return menuMapper.selectMenuByName(menuName, startLine, pageCount);
    }

    @Override
    public int findAllMenuByName(String menuName) {
        return menuMapper.selectAllMenuByName(menuName);
    }

    @Override
    public List<Menu> findAllMenuByStatue() {
        return menuMapper.selectAllMenuByStatue();
    }


}
