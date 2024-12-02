package com.shoestore.client.service.impl;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 10:43 PM
    ProjectName: Client
*/


import com.shoestore.client.dto.request.CategoryDTO;
import com.shoestore.client.dto.request.CategoryProductCountDTO;
import com.shoestore.client.dto.response.CategoryResponseDTO;
import com.shoestore.client.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<CategoryDTO> getAllCategory() {
        String apiUrl="http://localhost:8080/products/add";
        ResponseEntity<CategoryResponseDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, CategoryResponseDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody().getCategoryDTOs();
    }
    @Override
    public List<CategoryProductCountDTO> getCountProduct() {
        String apiUrl = "http://localhost:8080/category/count";

        // Gọi API trả về dữ liệu
        ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, Map.class);

        // Lấy dữ liệu mảng 'categories' từ body của response
        List<List<Object>> categoriesData = (List<List<Object>>) response.getBody().get("categories");

        // Chuyển đổi dữ liệu mảng thành các đối tượng CategoryProductCountDTO
        List<CategoryProductCountDTO> categories = new ArrayList<>();

        for (List<Object> categoryData : categoriesData) {
            CategoryProductCountDTO category = new CategoryProductCountDTO();
            category.setCategoryID((Integer) categoryData.get(0));
            category.setName((String) categoryData.get(1));
            category.setDescription((String) categoryData.get(2));
            category.setProductCount((Integer) categoryData.get(3));
            categories.add(category);
        }

        return categories;
    }
}
