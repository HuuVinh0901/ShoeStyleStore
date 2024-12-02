package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Address;
import com.shoestore.Server.entities.Cart;
import com.shoestore.Server.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String,Object>> getAddressByUserId(@PathVariable int id) {
        List<Address> addresses=addressService.getAddressByUserId(id);
        Map<String,Object> response= new HashMap<>();
        response.put("address",addresses);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable int id) {
        Address address=addressService.getById(id);
        return ResponseEntity.ok(address);
    }
}
