package com.shoestore.Server.service;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 9:21 PM
    ProjectName: Server
*/


import com.shoestore.Server.entities.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier getSupplier(int id);
    public List<Supplier> getAllSupplier();
}
