package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Voucher;
import com.shoestore.Server.repositories.VoucherRepository;
import com.shoestore.Server.service.VoucherService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vouchers")
public class VoucherController {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherService voucherService;

//    @GetMapping("/search")
//    public ResponseEntity<Map<String, Object>> searchVouchers(
//            @RequestParam(required = false) String startDate,
//            @RequestParam(required = false) String endDate
//    ) {
//        LocalDate start = null;
//        LocalDate end = null;
//        try {
//            if (startDate != null && !startDate.isEmpty()) {
//                start = LocalDate.parse(startDate);
//            }
//            if (endDate != null && !endDate.isEmpty()) {
//                end = LocalDate.parse(endDate);
//            }
//        } catch (Exception e) {
//            System.out.println("Lỗi khi chuyển đổi ngày: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Ngày không hợp lệ"));
//        }
//        List<Voucher> vouchers = voucherService.findVoucherByCodeOrDate(start, end);
//
//        if (vouchers.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(Map.of("message", "Không có voucher nào trong khoảng thời gian này"));
//        }
//
//        // Chuẩn bị response trả về cho client
//        Map<String, Object> response = new HashMap<>();
//        response.put("vouchers", vouchers);
//
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/add")
    public ResponseEntity<Voucher> addVoucher(@RequestBody Voucher voucherDTO) {
        Voucher savedVoucher = voucherService.addVoucher(voucherDTO);
        return ResponseEntity.ok(savedVoucher);
    }

    // Phương thức GET để lấy thông  tin voucher theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Voucher> getVoucherById(@PathVariable int id) {
        Optional<Voucher> voucher = voucherRepository.findById(id);
        if (voucher.isPresent()) {
            return ResponseEntity.ok(voucher.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voucher> updateVoucher(@PathVariable int id, @RequestBody Voucher voucher) {
        Voucher updatedVoucher = voucherService.updateVoucher(id, voucher);
        if (updatedVoucher != null) {
            return ResponseEntity.ok(updatedVoucher);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVoucher(@PathVariable("id") int id) {
        voucherService.deleteVoucher(id);
        System.out.println("voucher deleted  : ");
        return ResponseEntity.ok("Voucher deleted");
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getVouchers(
            @RequestParam(required = false, defaultValue = "all") String status,
            @RequestParam(required = false, defaultValue = "") String search
    ) {
        // Lấy danh sách Voucher từ service
        List<Voucher> vouchers;

        // Nếu trạng thái là "all", gọi phương thức getAllVouchers từ service
        if ("all".equals(status)) {
            // Lấy tất cả các voucher và lọc theo search (nếu có)
            vouchers = voucherService.getAllVouchers().stream()
                    .filter(v -> v.getName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            // Nếu trạng thái không phải "all", gọi phương thức từ voucherRepository với status và search
            vouchers = voucherRepository.findByStatusAndNameContainingIgnoreCase(status, search);
        }

        // Chuẩn bị response trả về cho client
        Map<String, Object> response = new HashMap<>();
        response.put("vouchers", vouchers);  // Danh sách voucher đã lọc

        return ResponseEntity.ok(response);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Voucher>> searchVouchers(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Voucher> vouchers = voucherService.searchVouchers(startDate, endDate);
        return new ResponseEntity<>(vouchers, HttpStatus.OK);
    }




}
