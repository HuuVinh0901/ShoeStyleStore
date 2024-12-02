package com.shoestore.Server.service.impl;


/*
    @author: Đào Thanh Phú
    Date: 11/20/2024
    Time: 9:42 PM
    ProjectName: Server
*/


import com.shoestore.Server.entities.Supplier;
import com.shoestore.Server.repositories.SupplierRepository;
import com.shoestore.Server.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    @Override
    public Supplier getSupplier(int id) {
        return supplierRepository.findBySupplierID(id);
    }
    @Override
    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }
}
