package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.request.ProductDetailDTO;
import com.shoestore.client.service.ProductDetailService;
import com.shoestore.client.service.ProductService;
import com.shoestore.client.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Controller
public class ProductDetailController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDetailService productDetailService;
    @GetMapping("/product-details/{id}")
    public String showProductDetails(@PathVariable("id") int id, Model model) {
        ProductDTO product =productService.getProductByIdForDetail(id);
        List<ProductDetailDTO> productDetails=productDetailService.getProductDetailByProduct(id);
        model.addAttribute("product", product);
        model.addAttribute("productDetail", productDetails);
        return "page/Customer/ProductDetail";
    }
}
