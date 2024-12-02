package com.shoestore.Server.controller;


/*
    @author: Đào Thanh Phú
    Date: 11/26/2024
    Time: 3:54 PM
    ProjectName: Server
*/

import com.shoestore.Server.entities.Review;
import com.shoestore.Server.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllReview() {
        List<Review> reviews = reviewService.getAllReview();
        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviews);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id) {
        boolean isDeleted = reviewService.deleteReview(id);
        if (isDeleted) {
            return ResponseEntity.ok("Xóa thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    @PostMapping("/rating/{rating}")
    public ResponseEntity<Map<String,Object>> getReviewByRating(@PathVariable int rating) {
        List<Review> reviews = reviewService.getReviewByRating(rating);
        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviews);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin")
    public ResponseEntity<Map<String, Object>> getAllReviewForAdmin(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        // Tạo Pageable từ tham số page và size
        Pageable pageable = PageRequest.of(page, size);

        // Gọi service để lấy dữ liệu phân trang
        Page<Review> reviewPage = reviewService.getAllReviewForAdmin(pageable);

        // Chuẩn bị response
        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviewPage.getContent());  // Dữ liệu của trang hiện tại
        response.put("totalItems", reviewPage.getTotalElements());  // Tổng số bản ghi
        response.put("totalPages", reviewPage.getTotalPages());  // Tổng số trang
        response.put("currentPage", page);  // Trang hiện tại

        return ResponseEntity.ok(response);
    }

    @GetMapping("/findreviews")
    public ResponseEntity<Map<String, Object>> findReviews(
            @RequestParam(required = false) Integer rating, // Cho phép null
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String nameProduct,
            @RequestParam(required = false) String sortOrder) {

        if (nameProduct != null) {
            // Giải mã giá trị của nameProduct, chuyển %20 thành dấu cách
            nameProduct = URLDecoder.decode(nameProduct, StandardCharsets.UTF_8);
        }

        System.out.println("Name Product: " + nameProduct);

        Pageable pageable = PageRequest.of(page, size);

        // Gọi phương thức service để lấy kết quả
        Page<Review> reviewPage = reviewService.findReviews(rating, nameProduct, sortOrder, pageable);

        // Chuẩn bị response
        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviewPage.getContent());
        response.put("totalItems", reviewPage.getTotalElements());
        response.put("totalPages", reviewPage.getTotalPages());
        response.put("currentPage", page);

        return ResponseEntity.ok(response);
    }

}
