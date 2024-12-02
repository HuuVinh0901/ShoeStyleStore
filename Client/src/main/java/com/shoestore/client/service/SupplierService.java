package com.shoestore.client.service;


/*
    @author: Đào Thanh Phú
    Date: 11/21/2024
    Time: 2:01 PM
    ProjectName: Client
*/

import com.shoestore.client.dto.request.SupplierDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService {
    public List<SupplierDTO> getAllSupplier();
}
