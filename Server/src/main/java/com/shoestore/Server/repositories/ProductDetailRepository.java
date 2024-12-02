package com.shoestore.Server.repositories;


import com.shoestore.Server.entities.Color;
import com.shoestore.Server.entities.ProductDetail;
import com.shoestore.Server.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Integer> {
    @Query("SELECT pd FROM ProductDetail pd " +
            "JOIN pd.product p " +
            "WHERE p.productID = :productId " +
            "AND pd.color = :color " +
            "AND pd.size = :size")
    Optional<ProductDetail> findByProductIdAndColorAndSize(int productId, Color color, Size size);

    List<ProductDetail> findByProduct_ProductID(int productID);

    @Query("SELECT pd FROM ProductDetail pd WHERE pd.product.productID = :productID AND pd.color = :color AND pd.size = :size")
    ProductDetail findOneByColorSizeAndProductId(
            @Param("productID") int productID,
            @Param("color") Color color,
            @Param("size") Size size
    );


}


