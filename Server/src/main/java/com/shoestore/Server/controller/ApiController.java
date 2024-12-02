package com.shoestore.Server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/greeting")
    public String greeting() {
        return "Chào mừng đến với API của tôi!";
    }
}
