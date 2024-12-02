package com.shoestore.client.service.impl;


/*
    @author: Đào Thanh Phú
    Date: 11/21/2024
    Time: 2:21 PM
    ProjectName: Client
*/


import com.shoestore.client.dto.request.BrandDTO;
import com.shoestore.client.dto.response.BrandResponseDTO;
import com.shoestore.client.dto.response.CategoryResponseDTO;
import com.shoestore.client.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<BrandDTO> getAllBrand() {
        String apiUrl="http://localhost:8080/products/add";
        ResponseEntity<BrandResponseDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, BrandResponseDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody().getBrandDTOs();
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        String apiUrl="http://localhost:8080/brands";
        ResponseEntity<BrandResponseDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, BrandResponseDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody().getBrandDTOss();
    }
}
