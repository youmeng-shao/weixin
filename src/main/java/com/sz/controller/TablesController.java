package com.sz.controller;


import com.sz.pojo.Category;
import com.sz.pojo.QueueMessage;
import com.sz.pojo.Tables;
import com.sz.service.CategoryService;
import com.sz.service.TablesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/tables")
public class TablesController {

    @Autowired
    private TablesService tablesService;

    @Autowired
    private CategoryService categoryService;

    private List<Tables> tablesList;

    private Integer preCategoryId;


    @RequestMapping(value = "/add")
    public String addTable(Tables table){
        // addTable函数的参数是一个tables对象
        // 前端需要传一个tableId， 这个tableId是假的，主要是
        // 为了方面封装table， 需要把这个tableId置为null
        System.out.println("新增table"+table);
        table.setTableId(null);
        tablesService.addTables(table);
        categoryService.addCount(table.getCategoryId());
        return "forward:/tables/findAll";
        // 转发到餐桌列表函数，有餐桌列表函数转发到列表页面
    }

    @RequestMapping(value = "/find")
    public String findTable(@Param("tableId") Integer tableId, Model model) {
        // findtable函数的参数是需要查找的tables的id
        Tables table = tablesService.findById(tableId);
        // 先存储可能会修改餐桌类型的id
        preCategoryId = table.getCategoryId();

        System.out.println(table);
        model.addAttribute("findTable", table);
        return "editTable";
        // 返回到tables的编辑页面
    }

    @RequestMapping(value = "/update")
    public String editTable(Tables table) {
        // editTable函数接收一个Tables对象
//        Tables table = new Tables();
//        table.setTableId(2);
//        table.setInnerTable("454");
//        table.setState(1);
//        table.setCategoryId(3);
        System.out.println(table);
        tablesService.editTables(table);

        // 先减少该餐桌类型的数量，然后增加某个类型的数量。
        categoryService.deleteCount(preCategoryId);
        categoryService.addCount(table.getCategoryId());

        return "forward:/tables/findAll";
        // 转发到餐桌列表函数，有餐桌列表函数转发到列表页面
    }

    @RequestMapping(value = "/delete")
    public String deleteTable(@Param("tableId") Integer tableId) {
        //deleteTable函数接收前端页面传过来的需要删除的tableId
        //int tableId = 2;
        // 饭桌类型的数量减一
        Tables table = tablesService.findById(tableId);
        categoryService.deleteCount(table.getCategoryId());

        tablesService.deleteById(tableId);
        return "forward:/tables/findAll";
        // 转发到餐桌列表函数，有餐桌列表函数转发到列表页面
    }

    @RequestMapping(value = "/findAll")
    public String findAll(Model model) {
        // findAll函数不需要接收任何前端数据
        // 但需要把找到的餐桌保存到List集合中，List集合保存到模型中，前端获取到，然后进行展示
        tablesList = new ArrayList<>();
        tablesList = tablesService.findAll();
        System.out.println(tablesList);
        model.addAttribute("allTables", tablesList);
        return "home";
        // 返回到餐桌列表
    }


    /*****************微信小程序端需要的功能***********************/

    /**
     * json返回数据餐桌类型的详细描述，餐桌内部桌号，餐桌的主键id
     *
     *
    */
    private List<QueueMessage> listQueueMessage;

    @RequestMapping(value = "/queueMessage")
    @ResponseBody
    public List<QueueMessage> getQueueMessage() {
        listQueueMessage = new ArrayList<>();

        // 获取信息
        for (int i=1; i<=4; ++i) {
            QueueMessage tempQueueMessage = new QueueMessage();
            Category category = categoryService.findCategoryById(i);
            tempQueueMessage.setDescription(category.getDescription());
            Tables table = tablesService.findTablesByCategoryId(i);

            if (table==null) {
                tempQueueMessage.setQueueInnerTale("满");
                tempQueueMessage.setTableId(-1);
            } else {
                tempQueueMessage.setQueueInnerTale(table.getInnerTable());
                tempQueueMessage.setTableId(table.getTableId());
            }
            listQueueMessage.add(tempQueueMessage);

        }

        return listQueueMessage;
    }

    /**
     * 根据用户选择的餐桌tableId返回 innerTable, description
     *
     *
     * */
    @RequestMapping(value = "/wxdetail")
    @ResponseBody
    public Map<String, String> wxFindTablesById(@RequestParam("tableId") Integer tableId) {
        Map<String, String> detailMap = new HashMap<>();

        Tables detailTable = tablesService.findById(tableId);
        Category detailCatogory = categoryService.findCategoryById(detailTable.getCategoryId());

        detailMap.put("innerTable", detailTable.getInnerTable());
        detailMap.put("description", detailCatogory.getDescription());

        return detailMap;
    }

    @RequestMapping(value = "/state")
    @ResponseBody
    public Map<String, String> changeState(@RequestParam("tableId") Integer tableId,
                                           @RequestParam(value = "currentTableId",
                                                   required = false) Integer currentTableId) {
        if (currentTableId!=null) {
            // 如果需要改变桌号，那么把已经选择的桌号置为0，未使用状态
            tablesService.editCurrentTableIdState(currentTableId);
        }

        tablesService.editTablesState(tableId);
        Map<String, String> stateMap = new HashMap<>();
        stateMap.put("state", "success");
        return stateMap;
    }

}
