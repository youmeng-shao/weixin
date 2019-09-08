package com.sz.service.impl;

import com.sz.mapper.TablesMapper;
import com.sz.pojo.Tables;
import com.sz.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tablesService")
public class TablesServiceImpl implements TablesService {

    @Autowired
    private TablesMapper tablesMapper;


    @Override
    public int addTables(Tables table) {

        return tablesMapper.saveTable(table);
    }

    @Override
    public Tables findById(Integer tableId) {

        return tablesMapper.selectById(tableId);
    }

    @Override
    public int editTables(Tables table) {

        return tablesMapper.updetaTables(table);
    }

    @Override
    public int deleteById(Integer tableId) {

        return tablesMapper.deleteBytableId(tableId);
    }

    @Override
    public List<Tables> findAll() {

        return tablesMapper.selectAll();
    }

    @Override
    public Tables findTablesByCategoryId(Integer categoryId) {
        return tablesMapper.selectTablesByCategoryId(categoryId);
    }

    @Override
    public void editTablesState(Integer tableId) {
        tablesMapper.updetaTablesState(tableId);
    }

    @Override
    public void editCurrentTableIdState(Integer currentTableId) {
        tablesMapper.updateCurrentTableIdState(currentTableId);
    }

}
