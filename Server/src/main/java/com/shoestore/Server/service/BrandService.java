package com.shoestore.Server.service;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 9:28 PM
    ProjectName: Server
*/


import com.shoestore.Server.entities.Brand;

import java.util.List;

public interface BrandService {
    Brand getBrand(int id);
    public List<Brand> getAllBrand();
}
