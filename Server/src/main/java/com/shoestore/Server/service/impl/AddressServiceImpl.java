package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Address;
import com.shoestore.Server.repositories.AddressRepository;
import com.shoestore.Server.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAddressByUserId(int id) {
        return addressRepository.findAddressByUserId(id);
    }

    @Override
    public Address getById(int id) {
        return addressRepository.findById(id).orElse(null);
    }
}
