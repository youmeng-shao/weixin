package com.sz.controller;


import com.sz.pojo.Counts;
import com.sz.pojo.User;
import com.sz.service.TablesService;
import com.sz.service.UserService;
import com.sz.utils.HttpRequest;
import com.sz.utils.PagesHelper;
import net.sf.json.JSONObject;
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
@RequestMapping(value = "/user")
public class UserController {

    // 注入UserService
    @Autowired
    private UserService userService;

    @Autowired
    private TablesService tablesService;


    public UserController(){

        System.out.println("userController 开始");
    }


    /**
     * 如果客人的饭菜已经上齐，（客人已经付过了钱），就可以点击下一位进行删除，
     * 删除主要的操作有：先删除user_menu 然后把餐桌的状态改为未使用，最后删除user中数据
     * */
    @RequestMapping(value = "/deleteUserQueue")
    public String deleteUserQueue(@RequestParam(value = "openId") String openId,
                                  @RequestParam(value = "currentPage") Integer currentPage){
        userService.deleteUserMenuByopenId(openId);

        // 查找出该用户的tableId, 改变该tableId 为未使用的状态，共其他客人使用
        int tableId = userService.findTableIdByopenId(openId);

        // 把餐桌的状态改为未使用
        tablesService.editCurrentTableIdState(tableId);

        // 最后删除user表中openId的数据
        userService.deleteUserByopenId(openId);

        return "forward:/user/findAllQueue?findAllQueue="+currentPage;
    }




    /**
     * 通过微信小程序的穿过来的code，获取openId，并返回到微信小程序
     * */

    @RequestMapping(value = "/login")
    @ResponseBody
    public Map<String, String> login(String code) {
        String appId = "wx12a825888119c7d5";
        String appSecret = "02e0199592110ff5fa6e4e9a41cf375c";
        // 请求参数
        String params = "appid="+appId+"&secret="+appSecret+"&js_code="+code
                +"&grant_type=authorization_code";

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String result = HttpRequest.sendGet(url, params);
        System.out.println("返回的结果："+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        String openId = jsonObject.getString("openid");

        System.out.println("微信小程序传过来的code"+code);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("openId", openId);
        // oiQMC5aWL3pv47_fLp8VafrPeC20
       return resultMap;
    }


    /**
     * 用户支付之后，加入到排队中
     * */

    @RequestMapping(value = "/pay")
    @ResponseBody
    public Map<String, Integer> goQueue(@RequestBody User user) {
        Map<String, Integer> queueMap = new HashMap<>();

        System.out.println("-----------------user:"+user);
        System.out.println("------------------munes:"+user.getMenus());

        for (int i=0; i<user.getCount().size(); ++i) {
            Counts counts = user.getCount().get(i);
            System.out.println("------------------count:"+counts.getNum());
        }

        userService.saveQueueUser(user);
        queueMap.put("success", 1);
        return queueMap;
    }

    @RequestMapping(value = "/findAllQueue")
    public String findQueueMessage(@RequestParam(value = "currentPage")
                                   Integer currentPage, Model model) {

        List<User> users = userService.findAllUser();

        int totalPages = PagesHelper.getPagesCount(users.size());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        List<User> viewUsers = PagesHelper.getViewUsers(users, currentPage);
        // 查询用户的innerTable，显示到前端页面
        List<String> viewInnerTable = new ArrayList<>();
        for (int i=0; i<viewUsers.size(); ++i) {
            String innerTable = tablesService.findById(viewUsers.
                    get(i).getTableId()).getInnerTable();
            viewInnerTable.add(innerTable);
        }

        model.addAttribute("viewUsers", viewUsers);
        model.addAttribute("innerTable", viewInnerTable);
        return "/userqueue/homeUser";
    }

}
