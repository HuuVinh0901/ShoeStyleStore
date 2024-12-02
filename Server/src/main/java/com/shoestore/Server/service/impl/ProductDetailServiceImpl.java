package com.shoestore.Server.service.impl;


import com.shoestore.Server.entities.Color;
import com.shoestore.Server.entities.ProductDetail;
import com.shoestore.Server.entities.Size;
import com.shoestore.Server.repositories.ProductDetailRepository;
import com.shoestore.Server.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private final ProductDetailRepository productDetailRepository;

    public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }



    @Override
    public ProductDetail addProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);

    }
    @Override
    public List<ProductDetail> getByProductId(int productID) {
        return productDetailRepository.findByProduct_ProductID(productID);

    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        // Kiểm tra tính hợp lệ (nếu cần)
        if (productDetail == null) {
            throw new IllegalArgumentException("ProductDetail không được để trống.");
        }
        // Lưu ProductDetail vào database
        return productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail getProductDetailById(int id) {
        return productDetailRepository.findById(id).orElse(null);
    }

    @Override
    public ProductDetail getProductDetailByProductIdAndColorAndSize(int productId, Color color, Size size) {
        return productDetailRepository.findOneByColorSizeAndProductId(productId, color, size);
    }
}
