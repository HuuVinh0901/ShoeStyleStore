package com.shoestore.Server.service.impl;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 9:29 PM
    ProjectName: Server
*/


import com.shoestore.Server.entities.Category;
import com.shoestore.Server.repositories.CategoryRepository;
import com.shoestore.Server.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category getCategory(int id) {
        return categoryRepository.findByCategoryID(id);
    }
    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Object[]> getCategoryWithCount() {
        return categoryRepository.getAllCategoriesWithProductCount();
    }
}
