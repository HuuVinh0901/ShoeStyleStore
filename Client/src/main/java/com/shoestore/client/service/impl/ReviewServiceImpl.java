package com.shoestore.client.service.impl;


/*
    @author: Đào Thanh Phú
    Date: 11/26/2024
    Time: 8:04 PM
    ProjectName: Client
*/


import com.shoestore.client.dto.request.ReviewDTO;
import com.shoestore.client.dto.response.ReviewResponseDTO;
import com.shoestore.client.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<ReviewDTO> getAllReview() {
        String apiUrl="http://localhost:8080/reviews";
        ResponseEntity<ReviewResponseDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, ReviewResponseDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody().getReviewDTOs();
    }

    @Override
    public ReviewResponseDTO getReviewByRating(int rating, int page, int size) {
        // Xây dựng URL cho API với tham số phân trang và rating
        String apiUrl = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/reviews/rating/{rating}")
                .queryParam("page", page)
                .queryParam("size", size)
                .buildAndExpand(rating)
                .toUriString();

        // Gọi API với RestTemplate
        ResponseEntity<ReviewResponseDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, ReviewResponseDTO.class
        );

        // Log response body (Có thể bỏ qua trong môi trường production)
        System.out.println("Response Body: " + response.getBody());

        // Trả về danh sách reviewDTO từ response
        return response.getBody();
    }

    @Override
    public ReviewResponseDTO findReviews(Integer rating, int page, int size, String nameProduct, String sortOrder) {
        // Xây dựng URL với tất cả tham số, kể cả rating (cho phép null)
        String apiUrl = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/reviews/findreviews")
                .queryParam("rating", rating)  // Rating có thể null
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("nameProduct", nameProduct)
                .queryParam("sortOrder", sortOrder)
                .toUriString();

        // Gọi API với RestTemplate
        ResponseEntity<ReviewResponseDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, ReviewResponseDTO.class
        );

        // Log response body (Có thể bỏ qua trong môi trường production)
        System.out.println("Response Body: " + response.getBody());

        // Trả về danh sách reviewDTO từ response
        return response.getBody();
    }
}
