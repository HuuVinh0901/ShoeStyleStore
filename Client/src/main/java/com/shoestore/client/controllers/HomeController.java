package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.BrandDTO;
import com.shoestore.client.dto.request.ProductHomeDTO;
import com.shoestore.client.service.BrandService;
import com.shoestore.client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
  @Autowired
  private ProductService productService;
  @Autowired
  private BrandService brandService;

  @GetMapping("/customer/home")
  public String home(Model model) {
    // Fetch Top 10 Best Sellers, New Arrivals, and Trending Products
    List<ProductHomeDTO> top10BestSellers = productService.getTop10BestSellers();
    List<ProductHomeDTO> top10NewArrivals = productService.getTop10NewArrivals();
    List<ProductHomeDTO> top10Trending = productService.getTop10Trending();
    List<BrandDTO> brands = brandService.getAllBrand();
    System.out.println(top10BestSellers);
    System.out.println(top10NewArrivals);
    System.out.println(top10Trending);
    System.out.println(brands);
    // Add the data to the model to pass to the Thymeleaf template
    model.addAttribute("top10BestSellers", top10BestSellers);
    model.addAttribute("top10NewArrivals", top10NewArrivals);
    model.addAttribute("top10Trending", top10Trending);
    model.addAttribute("brands", brands);
    return "page/Customer/Home"; // Return the home page view
  }
}
