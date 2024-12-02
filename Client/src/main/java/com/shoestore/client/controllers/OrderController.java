package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.OrderDTO;
import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.request.UserDTO;
import com.shoestore.client.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable; // Import đúng từ Spring Data

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/loyal-customers")
    @ResponseBody
    public List<Map<String, Object>> getTop10LoyalCustomers() {
        return orderService.getTop10LoyalCustomers();
    }
    @Autowired
    private HttpSession session;
    @GetMapping("/Home")
    public String showHomePage(Model model) {
        String today = LocalDate.now().toString();
        Map<String, Object> revenueStats = orderService.getRevenueStatistics(today, today);
        List<Map<String, Object>> loyalCustomer = orderService.getTop10LoyalCustomers();
        Map<String, Long> statistics = orderService.getOrderStatistics();
        List<ProductDTO> topSellingProducts = orderService.getTopSellingProducts("day");
        model.addAttribute("totalRevenue", revenueStats.get("totalRevenue"));
        model.addAttribute("totalOrders", revenueStats.get("totalOrders"));
        model.addAttribute("startDate", today);
        model.addAttribute("endDate", today);
        model.addAttribute("loyalCustomer", loyalCustomer);
        model.addAttribute("products", topSellingProducts);
        model.addAttribute("statistics", statistics);

        Map<String, Object> revenueYear = orderService.getYearlyRevenue();
        Object totalRevenueObj = revenueYear.get("totalRevenue");
        long totalRevenue = (totalRevenueObj instanceof Number) ? ((Number) totalRevenueObj).longValue() : 0;
        // Định dạng số totalRevenue với dấu chấm làm phân cách hàng nghìn
        DecimalFormat formatter = new DecimalFormat("#.###");
        formatter.setGroupingUsed(true);
        formatter.setGroupingSize(3);
        formatter.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.GERMANY));
        String formattedRevenue = formatter.format(totalRevenue);
        System.out.println("Formatted Revenue: " + formattedRevenue);
        // Truyền giá trị đã định dạng vào model
        revenueYear.put("totalRevenueYear", formattedRevenue);
        model.addAttribute("totalOrdersYear", revenueYear.get("totalQuantity"));
        model.addAttribute("revenueYear", revenueYear);

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            System.out.println(user.getName());
            model.addAttribute("userName", user.getName());
        } else {
            model.addAttribute("userName", "Guest");
        }
        return "page/Admin/TrangChuQuanLy";
    }

    // thống kê sản phẩm bán chạy
    @GetMapping("/top-selling")
    @ResponseBody
    public Map<String, Object> getTopSellingProducts(
            @RequestParam String type,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size) {
        List<ProductDTO> products = orderService.getTopSellingProducts(type);
        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("prevPage", page - 1);
        response.put("nextPage", page + 1);
        response.put("currentPage", page);
        return response;
    }

    // thống kê doanh thu dựa trên hóa đơn
    @GetMapping("/thong-ke")
    @ResponseBody
    public Map<String, Object> getRevenueStatistics(@RequestParam String startDate, @RequestParam String endDate) {
        Map<String, Object> revenueStats = orderService.getRevenueStatistics(startDate, endDate);
        return revenueStats;
    }

    // hiển thị danh sách đơn hàng
    @GetMapping("/view")
    public String showOrderList(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderDTO> ordersPage = orderService.getOrdersWithPagination(pageable);
        model.addAttribute("ordersPage", ordersPage);
        return "page/Admin/QuanLyDonHang";
    }

}