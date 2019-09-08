package com.sz.mapper;

import com.sz.pojo.Category;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {

    /**
     * 餐桌类型的方法只有2个，一个是某个餐桌类型增加了，一个是某个餐桌类型减少了
     * 都是根据传过来的id进行操作的
     *
     * */
    int insertCategoryCountById(Integer categoryId);

    int deleteCategoryCountById(Integer categoryId);

    Category selectCategoryById(@Param("categoryId") Integer categoryId);

}
