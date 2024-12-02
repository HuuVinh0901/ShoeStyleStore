package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.*;
import com.shoestore.client.service.BrandService;
import com.shoestore.client.service.CategoryService;
import com.shoestore.client.service.ProductService;
import com.shoestore.client.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private BrandService brandService;

    @GetMapping("/product-search/filtered")
    public String showProducts(
            @RequestParam(required = false) List<Integer> categoryIds,  // List category IDs
            @RequestParam(required = false) List<Integer> brandIds,     // List brand IDs
            @RequestParam(required = false) List<String> colors,         // List colors
            @RequestParam(required = false) List<String> sizes,          // List sizes
//            @RequestParam(required = false) String  price,
            @RequestParam(required = false) String  sortBy,
            Model model) {
//        Double minPrice = null;
//        Double maxPrice = null;
//        if (price != null) {
//            switch (price) {
//                case "under500":
//                    minPrice = 0.0;
//                    maxPrice = 500000.0;
//                    break;
//                case "500-1000":
//                    minPrice = 500000.0;
//                    maxPrice = 1000000.0;
//                    break;
//                case "1000-2000":
//                    minPrice = 10000000.0;
//                    maxPrice = 20000000.0;
//                    break;
//                case "over2000":
//                    minPrice = 20000000.0;
//                    maxPrice = Double.MAX_VALUE;  // Không giới hạn maxPrice
//                    break;
//            }
//        }

        List<ProductDTO> products;

        // Kiểm tra xem có filter nào không
        if ((categoryIds == null || categoryIds.isEmpty()) &&
                (brandIds == null || brandIds.isEmpty()) &&
                (colors == null || colors.isEmpty()) &&
                (sizes == null || sizes.isEmpty())
               && sortBy ==null)
        {
            // Nếu không có filter, lấy toàn bộ sản phẩm
            products = productService.getAllProduct();
        } else {
            // Nếu có filter, gọi service để lấy sản phẩm theo filters
            products = productService.getFilteredProducts(categoryIds, brandIds, colors, sizes,sortBy);

        }
        System.out.println(products);
        // Thêm danh sách sản phẩm vào model
        model.addAttribute("products", products);

        // Lấy danh sách các category và brand để hiển thị filter options
        List<CategoryProductCountDTO> categories = categoryService.getCountProduct();
        model.addAttribute("categories", categories);
        List<BrandDTO> brands = brandService.getAllBrand();
        model.addAttribute("brands", brands);

        // Thêm các size và color options vào model
        SizeDTO[] size = SizeDTO.values();
        model.addAttribute("sizes", size);
        ColorDTO[] color = ColorDTO.values();
        model.addAttribute("colors", color);


        return "page/Customer/SearchFragment";
    }

    @GetMapping("/customer/product-search")
    public String showProducts(

            Model model) {
        List<ProductDTO> products=productService.getAllProduct();
        model.addAttribute("products", products);
        List<CategoryProductCountDTO> categories = categoryService.getCountProduct();
        model.addAttribute("categories", categories);
        List<BrandDTO> brands = brandService.getAllBrand();
        model.addAttribute("brands", brands);

        // Thêm các size và color options vào model
        SizeDTO[] size = SizeDTO.values();
        model.addAttribute("sizes", size);
        ColorDTO[] color = ColorDTO.values();
        model.addAttribute("colors", color);

        return "page/Customer/Search";
    }
}
