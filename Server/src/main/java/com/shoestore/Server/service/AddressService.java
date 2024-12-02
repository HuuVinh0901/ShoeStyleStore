package com.shoestore.Server.service;

import com.shoestore.Server.entities.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAddressByUserId(int id);
    Address getById(int id);
}
