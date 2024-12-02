package com.shoestore.client.service.impl;


/*
    @author: Đào Thanh Phú
    Date: 11/21/2024
    Time: 2:02 PM
    ProjectName: Client
*/


import com.shoestore.client.dto.request.SupplierDTO;
import com.shoestore.client.dto.response.CategoryResponseDTO;
import com.shoestore.client.dto.response.SupplierResponseDTO;
import com.shoestore.client.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<SupplierDTO> getAllSupplier() {
        String apiUrl="http://localhost:8080/products/add";
        ResponseEntity<SupplierResponseDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, SupplierResponseDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody().getSupplierDTOs();
    }
}
