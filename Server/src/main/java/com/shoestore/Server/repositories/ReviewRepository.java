package com.shoestore.Server.repositories;


/*
    @author: Đào Thanh Phú
    Date: 11/26/2024
    Time: 3:50 PM
    ProjectName: Server
*/

import com.shoestore.Server.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT r FROM Review r WHERE r.start = :rating")
    List<Review> getAllReviewByRating(@Param("rating") int rating);

    @Query("SELECT r FROM Review r " +
            "JOIN r.product p " +
            "WHERE (:rating IS NULL OR r.start = :rating) " +
            "AND (:nameProduct IS NULL OR p.productName LIKE %:nameProduct%) " +
            "ORDER BY r.createDate ASC")
    Page<Review> findReviewsByOldDate(@Param("rating") Integer rating,
                                      @Param("nameProduct") String nameProduct,
                                      Pageable pageable);


    @Query("SELECT r FROM Review r " +
            "JOIN r.product p " +
            "WHERE (:rating IS NULL OR r.start = :rating) " +
            "AND (:nameProduct IS NULL OR p.productName LIKE %:nameProduct%) " +
            "ORDER BY r.createDate DESC")
    Page<Review> findReviewsByNewDate(@Param("rating") Integer rating,
                                      @Param("nameProduct") String nameProduct,
                                      Pageable pageable);

    @Query("SELECT r FROM Review r " +
            "JOIN r.product p " +
            "WHERE (:rating IS NULL OR r.start = :rating) " +
            "AND (:nameProduct IS NULL OR p.productName LIKE %:nameProduct%)")
    Page<Review> findReviewNotDate(@Param("rating") Integer rating,
                                   @Param("nameProduct") String nameProduct,
                                   Pageable pageable);

}
