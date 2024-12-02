package com.shoestore.Server.repositories;

import com.shoestore.Server.dto.ProductDTO;
import com.shoestore.Server.entities.Color;
import com.shoestore.Server.entities.Product;
import com.shoestore.Server.entities.ProductDetail;
import com.shoestore.Server.entities.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> , JpaSpecificationExecutor<Product> {
    @Query("SELECT p FROM Product p JOIN p.productDetails pd WHERE pd.productDetailID = :productDetailID")
    Product findProductByProductDetailId(@Param("productDetailID") int productDetailID);
    List<Product> findByProductID(int productID);


    // này của hieu
    @Query("SELECT p FROM Product p WHERE p.productID NOT IN :productIDs")
    List<Product> findByProductIDNotIn(@Param("productIDs") List<Integer> productIDs);


    @Query("SELECT p.imageURL FROM Product p JOIN p.imageURL pi WHERE p.productID = :productID")
    List<String> findImageUrlsByProductID(@Param("productID") int productID);

    // Best Sellers: Top 10 products sorted by total sales quantity
    @Query("SELECT new com.shoestore.Server.dto.ProductDTO(p.brand.name, p.category.name, p.createDate, " +
            "p.description, p.price, p.productID, p.productName, p.status, SUM(od.quantity)) " +
            "FROM Product p JOIN OrderDetail od ON p.productID = od.productDetail.productDetailID " +
            "GROUP BY p.productID, p.productName, p.price, p.status, p.brand.name, p.category.name, " +
            "p.description, p.createDate " +
            "ORDER BY SUM(od.quantity) DESC")
    List<ProductDTO> findTop10BestSellers(Pageable pageable);

    // New Arrivals: Top 10 newest products sorted by creation date
    @Query("SELECT new com.shoestore.Server.dto.ProductDTO(p.productID, p.productName, p.price, p.status, " +
            "p.brand.name, p.category.name, p.description, p.createDate) " +
            "FROM Product p ORDER BY p.createDate DESC")
    List<ProductDTO> findTop10NewArrivals(Pageable pageable);

    // Trending: Top 10 products added to carts the most
    @Query("SELECT new com.shoestore.Server.dto.ProductDTO(p.productID, p.productName, p.price, p.status, " +
            "p.brand.name, p.category.name, p.description, p.createDate, COUNT(ci.id)) " +
            "FROM Product p JOIN CartItem ci ON p.productID = ci.productDetail.productDetailID " +
            "GROUP BY p.productID, p.productName, p.price, p.status, p.brand.name, p.category.name, " +
            "p.description, p.createDate " +
            "ORDER BY COUNT(ci.id) DESC")
    List<ProductDTO> findTop10Trending(Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN p.brand b " +
            "LEFT JOIN p.supplier s " +
            "LEFT JOIN p.category c " +
            "WHERE (:keyword IS NULL OR " +
            "LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.supplierName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) ")
    Page<Product> findProducts(
            @Param("keyword") String keyword,
            Pageable pageable);

}
