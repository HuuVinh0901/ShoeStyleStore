package com.shoestore.Server.service.impl;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 9:41 PM
    ProjectName: Server
*/

import com.shoestore.Server.entities.Brand;
import com.shoestore.Server.repositories.BrandRepository;
import com.shoestore.Server.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    @Override
    public Brand getBrand(int id) {
        return brandRepository.findByBrandID(id);
    }
    @Override
    public List<Brand> getAllBrand() {

        return brandRepository.findAll();
    }
}
