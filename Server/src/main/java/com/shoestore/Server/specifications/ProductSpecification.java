package com.shoestore.Server.specifications;

import com.shoestore.Server.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {

    public static Specification<Product> hasCategories(List<Integer> categoryIds) {
        return (root, query, builder) -> categoryIds == null || categoryIds.isEmpty() ?
                null : root.get("category").get("categoryID").in(categoryIds);
    }


    public static Specification<Product> hasBrands(List<Integer> brandIds) {
        return (root, query, builder) -> brandIds == null || brandIds.isEmpty() ?
                null : root.get("brand").get("brandID").in(brandIds);
    }

    public static Specification<Product> hasColors(List<String> colors) {
        return (root, query, builder) -> colors == null || colors.isEmpty() ?
                null : root.get("productDetails").get("color").in(colors);
    }

    public static Specification<Product> hasSizes(List<String> sizes) {
        return (root, query, builder) -> sizes == null || sizes.isEmpty() ?
                null : root.get("productDetails").get("size").in(sizes);
    }

    public static Specification<Product> hasMinPrice(Double minPrice) {
        return (root, query, builder) -> minPrice == null ? null : builder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> hasMaxPrice(Double maxPrice) {
        return (root, query, builder) -> maxPrice == null ? null : builder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}
