package com.sz.service;

import com.sz.pojo.Tables;

import java.util.List;

public interface TablesService {

    /**
     * 增加餐桌
     *@Param Tables （包括内部桌号，对应的类型 state 都为0， 餐桌的id自动生成的
     *@return int
     * */
    int addTables(Tables table);

    /**
     * 根据tableId 查找table
     * @Param tableId
     * @return tables对象
     * */
    Tables findById(Integer tableId);

    /**
     * 更新餐桌的信息
     * @Param 对象tables
     * @return int
     * */
    int editTables(Tables table);

    /**
     * 删除餐桌
     * @Param tableId
     * @return int
     * */
    int deleteById(Integer tableId);
    /**
     * 查找所有的餐桌
     * @return List元素类型Tables
     * */
    List<Tables> findAll();

    /**
     * 返回每种类型的餐桌空闲信息，供顾客筛选
     * @Param 餐桌类型id
     * @return 餐桌对象tables
     * */
    Tables findTablesByCategoryId(Integer categoryId);

    /**
     * 改变餐桌的状态
     * */
    void editTablesState(Integer tableId);

    /**
     * 客人改变桌号，需要把当前选择的桌号状态置为 0
     * */
    void editCurrentTableIdState(Integer currentTableId);
}
