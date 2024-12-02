package com.shoestore.client.controllers;


/*
    @author: Đào Thanh Phú
    Date: 11/26/2024
    Time: 8:08 PM
    ProjectName: Client
*/


import com.shoestore.client.dto.request.ReviewDTO;
import com.shoestore.client.dto.response.ReviewResponseDTO;
import com.shoestore.client.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/product/review")
    public String listReviewForAdmin(Model model){
        List<ReviewDTO> reviews= reviewService.getAllReview();
        model.addAttribute("reviews",reviews);
        System.out.println(reviews);
        return "page/Admin/QuanLyBaiDanhGia";
    }

    @GetMapping("/page/admin/reviews")
    public String findReviews(Model model,
                              @RequestParam(required = false) Integer rating, // Rating có thể null
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "8") int size,
                              @RequestParam(required = false) String nameProduct,
                              @RequestParam(required = false) String sortOrder,
                              HttpServletRequest request) {

        // Gọi service để lấy dữ liệu phân trang theo rating, nameProduct và sortOrder
        ReviewResponseDTO reviews = reviewService.findReviews(rating, page, size, nameProduct, sortOrder);

        // Thêm danh sách đánh giá vào model
        model.addAttribute("reviews", reviews.getReviewDTOs());

        // Thêm các thông tin phân trang vào model
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reviews.getTotalPages());
        model.addAttribute("totalItems", reviews.getTotalItems());

        // Lấy phần đường dẫn và tham số truy vấn
        String requestURL = request.getRequestURL().toString(); // lấy URL gốc (đường dẫn)
        String queryString = request.getQueryString(); // lấy tham số truy vấn
        System.out.println("queryString: " + queryString);
        System.out.println("Request URL: " + requestURL);

        // Xử lý tham số page để tránh trùng lặp hoặc xử lý nếu queryString chỉ có page
        if (queryString != null) {
            if (queryString.equals("page=" + page)) {
                // Nếu queryString chỉ có page, loại bỏ page
                requestURL = requestURL.split("\\?")[0]; // Loại bỏ phần "?page=<số>"

            } else {
                // Loại bỏ các tham số page cũ nếu có
                queryString = queryString.replaceAll("(&|\\?)page=[^&]*", "");
                requestURL = requestURL.split("\\?")[0]; // Lấy phần URL trước dấu ?

                if (!queryString.isEmpty()) {
                    requestURL += "?" + queryString; // Thêm lại các tham số query khác
                }
            }
        }

        // Thêm tham số page vào URL


        // Thêm toàn bộ URL vào model
        model.addAttribute("requestURL", requestURL);


        // Kiểm tra kết quả trong console (tùy chỉnh cho mục đích debug)
        System.out.println("Total Reviews: " + reviews.getTotalItems());
        System.out.println("Total Pages: " + reviews.getTotalPages());
        System.out.println("Request URL: " + requestURL); // In ra toàn bộ URL

        // Trả về tên view để hiển thị danh sách đánh giá
        return "page/Admin/QuanLyBaiDanhGia";
    }

}
