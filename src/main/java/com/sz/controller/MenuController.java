package com.sz.controller;

import com.sz.pojo.Menu;
import com.sz.service.MenuService;
import com.sz.utils.MenuFileHelper;
import com.sz.utils.PagesHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // 可能需要设置一个属性，用来记录当前的currentPage
    private Integer recordCurrentPage=1;

    // 存贮分页查找出来的数据
    private List<Menu> menuList;

    // 判断当前页面是否是模糊查询
    private String currentLikemenuName = null;

    @RequestMapping(value = "/add")
    public String addMenu(@RequestParam("file")MultipartFile multipartFile,
                          Menu menu, HttpServletRequest request){
        //addMenu函数接收一个Menu的对象，然后进行添加, 需要把menuId的值置为null
        // 并且需要改变img的值。
        menu.setMenuId(null);
        System.out.println("menu:"+menu);

        if (multipartFile!=null&&!multipartFile.isEmpty()){
            // 得到上传路径
            String imgPath = request.getSession().getServletContext()
                    .getRealPath("/images/");
            System.out.println(imgPath);
            String newFileName = MenuFileHelper.uploadImgFile(multipartFile, imgPath);
            menu.setImage(newFileName);
            System.out.println(newFileName);
        }

        menuService.addMenu(menu);

        if (currentLikemenuName!=null) {
            // 当前如果过是在模糊查询页面点击的新增按钮，就跳转到模糊查询中
            return "forward:/menu/findByName";
        } else {
            // 如果不是
            return "forward:/menu/currentPageMenus";
            // 转发到currentPageMenus,

        }

    }

    @RequestMapping(value = "/delete")
    public String deleteMenu(@Param("menuId") Integer menuId) {
        // deleteMenu函数接收一个需要删除的menuId
        menuService.delteMenuById(menuId);
        System.out.println("删除成功！！！");

        if (currentLikemenuName!=null) {
            // 当前如果过是在模糊查询页面点击的新增按钮，就跳转到模糊查询中
            return "forward:/menu/findByName";
        } else {
            // 如果不是
            return "forward:/menu/currentPageMenus";
            // 转发到currentPageMenus,
        }

    }

    @RequestMapping(value = "/find")
    public String findMenu(@RequestParam("menuId") Integer menuId, Model model) {
        // findMenu函数接收一个menuId然后根据id进行查找，转发到编辑页面
        Menu menu = menuService.findMenuById(menuId);
        System.out.println("根据menuId找到的菜品："+menu);
        // 存储下来
        model.addAttribute("findMenu", menu);
        return "menu/editMenu";
    }

    @RequestMapping(value = "/update")
    public String saveMenu(@RequestParam("file")MultipartFile multipartFile,
                           Menu menu, HttpServletRequest request){
        // saveMenu函数接收一个Menu的对象，对编辑页面的保存
        System.out.println("需要保存的menu："+menu);
        if (multipartFile!=null&&!multipartFile.isEmpty()){
            // 得到上传路径
            String imgPath = request.getSession().getServletContext()
                    .getRealPath("/images/");
            System.out.println(imgPath);

            String newFileName = MenuFileHelper.uploadImgFile(multipartFile, imgPath);
            imgPath = imgPath + File.separator;
            // 删除原先的图片
            File file = new File(imgPath+menu.getImage());
            System.out.println("删除图片的路径："+imgPath+menu.getImage());
            if (file.exists()) {
                System.out.println("删除图片："+file.delete());
            }

            menu.setImage(newFileName);
            System.out.println(newFileName);
        }

        menuService.editMenu(menu);

        return "forward:/menu/currentPageMenus";
        // 需要转发到findAll函数中，再进行页面的跳转
    }

    @RequestMapping(value = "/currentPageMenus")
    public String findCurrentPageMenu(@RequestParam(value = "currentPage", required = false)
                                                  Integer currentPage,
                                      Model model) {
        // 防止记录了上一次的模糊查询
        currentLikemenuName = null;
        if (currentPage==null) {
            // 如果是从其他函数转发过来的，那么currentPage为null，需要把记录的值传给他
            currentPage = recordCurrentPage;
        } else {
            // 如果是从前端页面传过来的，那么需要记录一下，
            recordCurrentPage = currentPage;
        }
        int totalPages = 0;
        menuList = new ArrayList<>();

        totalPages = PagesHelper.getPagesCount(menuService.findMenuAll());
        int startLine = (currentPage-1)*PagesHelper.PAGES_COUNTS;
        menuList = menuService.findMenuPages(startLine, PagesHelper.PAGES_COUNTS);


        // 把数据保存到模型，然后前端页面进行显示
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageCounts", PagesHelper.PAGES_COUNTS);
        model.addAttribute("menuList", menuList);
        return "menu/homeMenu";
        // 转发到menuhome.jsp中
    }


    private Integer recordLikeNameCurrentPage = 1;

    @RequestMapping(value = "/findByName")
    public String getMenuByname(@RequestParam(value = "likeMenuName", required = false)
                                            String menuName,
                                Model model,
                                @RequestParam(value = "likeNameCurrentPage",required = false)
                                        Integer likeNameCurrentPage){

        // 是模糊查询
        if (likeNameCurrentPage==null) {
            likeNameCurrentPage = recordLikeNameCurrentPage;
        } else {
            recordLikeNameCurrentPage = likeNameCurrentPage;
        }

        menuList = new ArrayList<>();
        if (menuName==null) {
            menuName = currentLikemenuName;

        } else {
            currentLikemenuName = menuName;
        }

        int totalPages = 0;
        int startLine = (likeNameCurrentPage-1)*PagesHelper.PAGES_COUNTS;
        menuList = menuService.findMenuByName("%"+menuName+"%",startLine,
                PagesHelper.PAGES_COUNTS);
        totalPages = PagesHelper.getPagesCount(menuService.findAllMenuByName("%"+menuName+"%"));

//        保存需要查找的菜名
//        model.addAttribute()
        // 把数据保存到模型，然后前端页面进行显示
        model.addAttribute("pageCounts", PagesHelper.PAGES_COUNTS);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", likeNameCurrentPage);
        model.addAttribute("menuList", menuList);

        // 通知前端页面，属于模糊查询
        model.addAttribute("isLikeName", 1);
        return "menu/homeMenu";
    }

    /************微信小程序端************/

    /**
     * 用户查看菜单时，返回菜单List（menu对象)
     * */
    @RequestMapping(value = "/look")
    @ResponseBody
    public List<Menu> lookMenu() {
        List<Menu> menusByStatue = new ArrayList<>();
        menusByStatue = menuService.findAllMenuByStatue();
        for (int i=0; i<menusByStatue.size(); ++i) {
            Menu tempMenu = menusByStatue.get(i);
            String realImage = "http://localhost:8080/ssm/images/"+tempMenu.getImage();
            menusByStatue.get(i).setImage(realImage);
        }

        return menusByStatue;
    }


}
