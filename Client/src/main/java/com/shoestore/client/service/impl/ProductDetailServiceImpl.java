package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.request.ProductDetailDTO;
import com.shoestore.client.dto.response.ProductDetailResponseDTO;
import com.shoestore.client.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<ProductDetailDTO> getProductDetailByProduct(int productId) {
        String apiUrl = "http://localhost:8080/products-details/{id}";
        ResponseEntity<ProductDetailResponseDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, ProductDetailResponseDTO.class, productId
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody().getProductDetailDTOs();
    }

    @Override
    public ProductDetailDTO getProductDetailById(int id) {
        String apiUrl="http://localhost:8080/products-details/productDetailId/"+id;
        ResponseEntity<ProductDetailDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, ProductDetailDTO.class
        );
//        System.out.println("Response Body: " + response.getBody());
        return response.getBody();
    }
}
