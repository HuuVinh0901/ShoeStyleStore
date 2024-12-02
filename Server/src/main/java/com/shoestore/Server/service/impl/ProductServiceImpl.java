package com.shoestore.Server.service.impl;

import com.shoestore.Server.dto.ProductDTO;
import com.shoestore.Server.entities.Product;
import com.shoestore.Server.repositories.OrderDetailRepository;
import com.shoestore.Server.repositories.ProductRepository;
import com.shoestore.Server.service.ProductService;
import com.shoestore.Server.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    // này của hiếu
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findByProductDetailsId(int id) {
        return productRepository.findProductByProductDetailId(id);
    }

    @Override

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);

    }


    @Override
    public List<Product> getFilteredProducts(List<Integer> categoryIds, List<Integer> brandIds, List<String> colors, List<String> sizes, Double minPrice, Double maxPrice, String sortBy) {
        Specification<Product> spec = Specification.where(null);

        if (categoryIds != null && !categoryIds.isEmpty()) {
            spec = spec.and(ProductSpecification.hasCategories(categoryIds));
        }

        if (brandIds != null && !brandIds.isEmpty()) {
            spec = spec.and(ProductSpecification.hasBrands(brandIds));
        }

        if (colors != null && !colors.isEmpty()) {
            spec = spec.and(ProductSpecification.hasColors(colors));
        }

        if (sizes != null && !sizes.isEmpty()) {
            spec = spec.and(ProductSpecification.hasSizes(sizes));
        }

        if (minPrice != null) {
            spec = spec.and(ProductSpecification.hasMinPrice(minPrice));
        }

        if (maxPrice != null) {
            spec = spec.and(ProductSpecification.hasMaxPrice(maxPrice));
        }

        if (sortBy != null) {
            switch (sortBy) {
                case "Price: High-Low":
                    return productRepository.findAll(spec, Sort.by(Sort.Order.desc("price")));
                case "Price: Low-High":
                    return productRepository.findAll(spec, Sort.by(Sort.Order.asc("price")));
                case "Newest":
                    return productRepository.findAll(spec, Sort.by(Sort.Order.desc("createDate")));
                default:
                    return productRepository.findAll(spec);
            }
        }

        return productRepository.findAll(spec);
    }

    @Override
    public Page<Product> findProducts(String keyword, String sortBy, String order, Pageable pageable) {
        Pageable sortedPageable;

        // Kiểm tra nếu `sortBy` và `order` đều không null
        if (sortBy != null && order != null) {
            // Xác định hướng sắp xếp
            Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;

            // Xác định trường cần sắp xếp
            String sortField;
            if ("price".equalsIgnoreCase(sortBy)) {
                sortField = "price";
            } else {
                sortField = "createDate"; // Mặc định là sắp xếp theo createDate
            }

            // Tạo Pageable với thông tin sắp xếp
            sortedPageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(direction, sortField)
            );
        } else {
            // Nếu không có thông tin sắp xếp, dùng Pageable mặc định
            sortedPageable = pageable;
        }

        // Gọi repository với Pageable được điều chỉnh
        return productRepository.findProducts(keyword, sortedPageable);
    }


    // nay cua hieu
    public List<Product> getProductsNotInOrderDetail(int orderID) {
        List<Integer> productIDsInOrderDetail = orderDetailRepository.findProductIDsByOrderID(orderID);
        if (productIDsInOrderDetail.isEmpty()) {
            return productRepository.findAll();
        } else {

            return productRepository.findByProductIDNotIn(productIDsInOrderDetail);
        }
    }
    private List<ProductDTO> setImageURLs(List<ProductDTO> productDTOList) {
        productDTOList.forEach(productDTO -> {
            List<String> imageUrls = getImageUrlsByProductID(productDTO.getProductID());
            productDTO.setImageURL(imageUrls);
        });
        return productDTOList;
    }
    @Override
    public List<String> getImageUrlsByProductID(int id) {
        return productRepository.findImageUrlsByProductID(id);
    }

    @Override
    public List<ProductDTO> getTop10BestSellers() {
        return setImageURLs(productRepository.findTop10BestSellers(PageRequest.of(0, 10)));
    }

    @Override
    public List<ProductDTO> getTop10NewArrivals() {
        return setImageURLs(productRepository.findTop10NewArrivals(PageRequest.of(0, 10)));
    }

    @Override
    public List<ProductDTO> getTop10Trending() {
        return setImageURLs(productRepository.findTop10Trending(PageRequest.of(0, 10)));
    }



}
