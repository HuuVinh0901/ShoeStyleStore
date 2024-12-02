package com.shoestore.client.service;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 10:42 PM
    ProjectName: Client
*/

import com.shoestore.client.dto.request.CategoryDTO;
import com.shoestore.client.dto.request.CategoryProductCountDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<CategoryDTO> getAllCategory();
    List<CategoryProductCountDTO> getCountProduct();
}
