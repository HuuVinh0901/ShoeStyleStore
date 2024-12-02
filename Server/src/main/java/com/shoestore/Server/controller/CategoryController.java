package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Category;
import com.shoestore.Server.entities.Product;
import com.shoestore.Server.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/count") // Ánh xạ HTTP GET
    public ResponseEntity<Map<String,Object>> getAllCategoryWithCount(){
        List<Object[]> categories=categoryService.getCategoryWithCount();
        System.out.println(categories);
        Map<String,Object> response= new HashMap<>();
        response.put("categories",categories);
        return ResponseEntity.ok(response);
    }
}
