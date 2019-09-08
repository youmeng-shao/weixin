package com.sz.service.impl;

import com.sz.mapper.CategoryMapper;
import com.sz.pojo.Category;
import com.sz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int addCount(Integer categoryId) {

        return categoryMapper.insertCategoryCountById(categoryId);
    }

    @Override
    public int deleteCount(Integer categoryId) {
        return categoryMapper.deleteCategoryCountById(categoryId);
    }

    @Override
    public Category findCategoryById(Integer categoryId) {
        return categoryMapper.selectCategoryById(categoryId);
    }
}
