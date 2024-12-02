package com.shoestore.Server.service.impl;


/*
    @author: Đào Thanh Phú
    Date: 11/26/2024
    Time: 3:53 PM
    ProjectName: Server
*/


import com.shoestore.Server.entities.Review;
import com.shoestore.Server.repositories.ReviewRepository;
import com.shoestore.Server.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean deleteReview(int id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Review> getReviewByRating(int rating) {
        return reviewRepository.getAllReviewByRating(rating);
    }

    @Override
    public Page<Review> findReviews(Integer rating, String name, String date, Pageable pageable) {
        if ("old".equalsIgnoreCase(date)) {
            return reviewRepository.findReviewsByOldDate(rating, name, pageable); // Sắp xếp theo ngày tăng dần
        } else if ("new".equalsIgnoreCase(date)) {
            return reviewRepository.findReviewsByNewDate(rating, name, pageable); // Sắp xếp theo ngày giảm dần
        }
        return reviewRepository.findReviewNotDate(rating, name, pageable); // Không sắp xếp
    }

    @Override
    public Page<Review> getAllReviewForAdmin(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }
}
