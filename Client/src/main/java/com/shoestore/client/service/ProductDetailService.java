package com.shoestore.client.service;

import com.shoestore.client.dto.request.ProductDetailDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductDetailService {
    List<ProductDetailDTO> getProductDetailByProduct(int id);
    ProductDetailDTO getProductDetailById(int id);
}
