package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.AddressDTO;
import com.shoestore.client.dto.request.BrandDTO;
import com.shoestore.client.dto.response.AddressResponseDTO;
import com.shoestore.client.dto.response.BrandResponseDTO;
import com.shoestore.client.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<AddressDTO> getAddressByUserId(int id) {
        String apiUrl="http://localhost:8080/address/user/"+id;
        ResponseEntity<AddressResponseDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, AddressResponseDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody().getAddress();
    }
    @Override
    public AddressDTO getAddressById(int id) {
        String apiUrl="http://localhost:8080/address/"+id;
        ResponseEntity<AddressDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, AddressDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody();
    }
}
