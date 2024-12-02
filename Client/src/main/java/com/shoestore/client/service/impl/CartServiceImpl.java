package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.CartDTO;
import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CartDTO getCartByUserId(int id) {

        String apiUrl = "http://localhost:8080/cart/userid/" + id;
        ResponseEntity<CartDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, CartDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody();

    }
}
