package com.shoestore.client.service.impl;


import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.request.ProductHomeDTO;
import com.shoestore.client.dto.response.ProductHomeResponseDTO;
import com.shoestore.client.dto.response.ProductResponseDTO;
import com.shoestore.client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<ProductDTO> getAllProduct() {
        String apiUrl="http://localhost:8080/products";
        ResponseEntity<ProductResponseDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, ProductResponseDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody().getProductDTOs();
    }


    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        String apiUrl = "http://localhost:8080/products/add";
        ResponseEntity<ProductDTO> response = restTemplate.postForEntity(
                apiUrl, productDTO, ProductDTO.class
        );
        return response.getBody();
    }

    @Override
    public ProductDTO getProductByIdForDetail(int id) {
        String apiUrl="http://localhost:8080/products/detailFor/"+id;
        ResponseEntity<ProductDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, ProductDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody();
    }
    @Override
    public ProductDTO getProductByProductDetail(int id) {
        String apiUrl="http://localhost:8080/products/detailsId/"+id;
        ResponseEntity<ProductDTO> response= restTemplate.exchange(
                apiUrl, HttpMethod.GET,null, ProductDTO.class
        );
        return response.getBody();
    }


    @Override
    public List<ProductDTO> getFilteredProducts(List<Integer> categoryIds, List<Integer> brandIds, List<String> color, List<String> size,String sortBy) {
        StringBuilder apiUrl = new StringBuilder("http://localhost:8080/products/filtered");

        // Thêm các tham số vào URL nếu không null
        boolean hasParam = false;
        if (categoryIds != null && !categoryIds.isEmpty()) {
            apiUrl.append(hasParam ? "&" : "?").append("categoryIds=")
                    .append(String.join(",", categoryIds.stream().map(String::valueOf).toArray(String[]::new))); // Sử dụng categoryIds thay vì category
            hasParam = true;
        }
        if (brandIds != null && !brandIds.isEmpty()) {
            apiUrl.append(hasParam ? "&" : "?").append("brandIds=")
                    .append(String.join(",", brandIds.stream().map(String::valueOf).toArray(String[]::new))); // Sử dụng brandIds thay vì brand
            hasParam = true;
        }
        if (color != null && !color.isEmpty()) {
            apiUrl.append(hasParam ? "&" : "?").append("colors=")
                    .append(String.join(",", color));
            hasParam = true;
        }
        if (size != null && !size.isEmpty()) {
            apiUrl.append(hasParam ? "&" : "?").append("size=")
                    .append(String.join(",", size));
            hasParam = true;
        }
        if (sortBy != null) {
            try {
                // URL encode the sortBy parameter to handle special characters
                String encodedSortBy = URLEncoder.encode(sortBy, StandardCharsets.UTF_8.toString());
                apiUrl.append(hasParam ? "&" : "?").append("sortBy=").append(encodedSortBy);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();  // Handle exception if encoding fails
            }
        }
        System.out.println(apiUrl);
        String rawJson = restTemplate.getForObject(apiUrl.toString(), String.class);
        System.out.println("Raw JSON from API (before parsing): " + rawJson);
        ResponseEntity<ProductResponseDTO> response = restTemplate.exchange(
                apiUrl.toString(),
                HttpMethod.GET,
                null,
                ProductResponseDTO.class
        );
        System.out.println("Dữ liệu từ API (trước khi xử lý): " + response.getBody());
        // Trả về danh sách sản phẩm từ response body
        if (response.getBody() != null) {
            return response.getBody().getProductDTOs();
        }

        // Trả về danh sách rỗng nếu không có sản phẩm nào
        return new LinkedList<>();
    }

    @Override
    public ProductResponseDTO findProducts(String keyword, String sortBy, String order, int page, int size) {
        // Xây dựng URL với tất cả tham số, kể cả keyword (cho phép null)
        String apiUrl = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/products/findproducts")
                .queryParam("keyword", keyword) // Keyword có thể null
                .queryParam("page", page)       // Trang
                .queryParam("size", size)       // Kích thước mỗi trang
                .queryParam("sortBy", sortBy)   // Trường sắp xếp (có thể null)
                .queryParam("order", order)     // Thứ tự sắp xếp (có thể null)
                .toUriString();

        // Gọi API với RestTemplate
        ResponseEntity<ProductResponseDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, ProductResponseDTO.class
        );

        // Log response body (Có thể bỏ qua trong môi trường production)
        System.out.println("Response Body: " + response.getBody());

        // Trả về danh sách sản phẩm từ response
        return response.getBody();
    }


    // Lấy Top 10 sản phẩm bán chạy
    @Override
    public List<ProductHomeDTO> getTop10BestSellers() {
        String apiUrl = "http://localhost:8080/products/best-sellers";
        ResponseEntity<ProductHomeResponseDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, ProductHomeResponseDTO.class
        );
        return response.getBody().getBestSellers();
    }

    // Lấy Top 10 sản phẩm mới ra mắt
    @Override
    public List<ProductHomeDTO> getTop10NewArrivals() {
        String apiUrl = "http://localhost:8080/products/new-arrivals";
        ResponseEntity<ProductHomeResponseDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, ProductHomeResponseDTO.class
        );
        return response.getBody().getNewArrivals();
    }

    // Lấy Top 10 sản phẩm thịnh hành
    @Override
    public List<ProductHomeDTO> getTop10Trending() {
        String apiUrl = "http://localhost:8080/products/trending";
        ResponseEntity<ProductHomeResponseDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, ProductHomeResponseDTO.class
        );
        return response.getBody().getTrendingProducts();
    }
}
