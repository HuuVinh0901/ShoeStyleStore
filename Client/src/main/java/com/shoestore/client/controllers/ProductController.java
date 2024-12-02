package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.*;
import com.shoestore.client.dto.response.ProductResponseDTO;
import com.shoestore.client.service.BrandService;
import com.shoestore.client.service.CategoryService;
import com.shoestore.client.service.ProductService;
import com.shoestore.client.service.SupplierService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private BrandService brandService;

    @GetMapping("/page/admin/product")
    public String listProductsAdmin(Model model){
        List<ProductDTO> products= productService.getAllProduct();
        model.addAttribute("products",products);
        System.out.println(products);
        return "page/Admin/QuanLySanPham";
    }

    @GetMapping("/page/admin/product/add")
    public String listCategoryAdmin(Model model){
        List<CategoryDTO> categories= categoryService.getAllCategory();
        model.addAttribute("categories",categories);
        List<SupplierDTO> suppliers= supplierService.getAllSupplier();
        model.addAttribute("suppliers",suppliers);
        List<BrandDTO> brands= brandService.getAllBrand();
        model.addAttribute("brands",brands);
        System.out.println(categories);
        return "page/Admin/ThemSanPham";
    }

    @PostMapping("/page/admin/product/add")
    public String addProduct(ProductDTO productDTO, Model model) {
        try {
            // Gửi sản phẩm mới đến server để lưu
            ProductDTO savedProduct = productService.addProduct(productDTO);
            model.addAttribute("message", "Sản phẩm đã được thêm thành công!");
            return "redirect:/product"; // Chuyển hướng về danh sách sản phẩm
        } catch (Exception e) {
            model.addAttribute("error", "Có lỗi xảy ra khi thêm sản phẩm: " + e.getMessage());
            return "page/Admin/QuanLySanPham"; // Trả lại form thêm sản phẩm
        }
    }

    @GetMapping("/page/admin/detail/{id}")
    public String showProductDetail(@PathVariable int id, Model model) {
        ProductDTO productDTO = productService.getProductByIdForDetail(id);
        model.addAttribute("product", productDTO);
        return "page/Admin/ChiTietSanPham";
    }

    @GetMapping("/page/admin/update/{id}")
    public String showProductDetailUpdate(@PathVariable int id, Model model) {
        ProductDTO productDTO = productService.getProductByIdForDetail(id);
        model.addAttribute("product", productDTO);
        List<CategoryDTO> categories= categoryService.getAllCategory();
        model.addAttribute("categories",categories);
        List<SupplierDTO> suppliers= supplierService.getAllSupplier();
        model.addAttribute("suppliers",suppliers);
        List<BrandDTO> brands= brandService.getAllBrand();
        model.addAttribute("brands",brands);
        return "page/Admin/SuaSanPham";
    }

    @GetMapping("/page/admin/products")
    public String findProducts(Model model,
                               @RequestParam(required = false) String keyword, // Keyword có thể null
                               @RequestParam(defaultValue = "0") int page, // Trang mặc định là 0
                               @RequestParam(defaultValue = "6") int size, // Kích thước mặc định là 8
                               @RequestParam(required = false) String sortBy, // Trường sắp xếp có thể null
                               @RequestParam(required = false) String order,
                               @RequestParam(required = false) String sort,
                               HttpServletRequest request) {

        if (sort != null && !sort.isEmpty()) {
            String[] sortParams = sort.split("_");
            sortBy = sortParams[0];
            order = sortParams[1];
        }
        // Gọi service để lấy dữ liệu phân trang theo keyword, sortBy và order
        ProductResponseDTO products = productService.findProducts(keyword, sortBy, order, page, size);

        // Thêm danh sách sản phẩm vào model
        model.addAttribute("products", products.getProductDTOs());

        // Thêm các thông tin phân trang vào model
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("totalItems", products.getTotalItems());

        // Lấy phần đường dẫn và tham số truy vấn
        String requestURL = request.getRequestURL().toString(); // Lấy URL gốc (đường dẫn)
        String queryString = request.getQueryString(); // Lấy tham số truy vấn
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
        model.addAttribute("requestURL", requestURL);

        // Kiểm tra kết quả trong console (tùy chỉnh cho mục đích debug)
        System.out.println("Total Products: " + products.getTotalItems());
        System.out.println("Total Pages: " + products.getTotalPages());
        System.out.println("Request URL: " + requestURL); // In ra toàn bộ URL

        // Trả về tên view để hiển thị danh sách sản phẩm
        return "page/Admin/QuanLySanPham";
    }



}
