package com.shoestore.client.service;

import com.shoestore.client.dto.request.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAddressByUserId(int id);
    AddressDTO getAddressById(int id);
}
