package com.sz.service;

import com.sz.pojo.Category;

public interface CategoryService {

    int addCount(Integer categoryId);

    int deleteCount(Integer categoryId);

    Category findCategoryById(Integer categoryId);
}
