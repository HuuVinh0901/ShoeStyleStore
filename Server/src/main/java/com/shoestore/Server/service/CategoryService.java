package com.shoestore.Server.service;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 9:27 PM
    ProjectName: Server
*/


import com.shoestore.Server.entities.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(int id);
    public List<Category> getAllCategory();
    List<Object[]> getCategoryWithCount();
}
