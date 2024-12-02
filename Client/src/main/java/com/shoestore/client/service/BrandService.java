package com.shoestore.client.service;


/*
    @author: Đào Thanh Phú
    Date: 11/21/2024
    Time: 2:20 PM
    ProjectName: Client
*/


import com.shoestore.client.dto.request.BrandDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    public List<BrandDTO> getAllBrand();
    public List<BrandDTO> getAllBrands();
}
