package com.shoestore.client.controllers;
import com.shoestore.client.dto.request.VoucherDTO;
import com.shoestore.client.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


@Controller
@RequestMapping("/admin/vouchers")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;
    @GetMapping("/search")
    public ResponseEntity<List<VoucherDTO>> searchVouchers(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        LocalDate start = null;
        LocalDate end = null;

        try {
            if (startDate != null && !startDate.isEmpty()) {
                start = LocalDate.parse(startDate);
            }
            if (endDate != null && !endDate.isEmpty()) {
                end = LocalDate.parse(endDate);
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null); // Trả về lỗi nếu không thể phân tích ngày
        }

        List<VoucherDTO> vouchers = voucherService.searchVouchers(start, end);
        System.out.println(vouchers);
        return ResponseEntity.ok(vouchers);
    }
    @PostMapping("/add")
    public ResponseEntity<VoucherDTO> addVoucher(@RequestBody VoucherDTO voucherDTO) {
        // Thêm voucher vào cơ sở dữ liệu thông qua service
        VoucherDTO addedVoucher = voucherService.addVoucher(voucherDTO);

        if (addedVoucher != null) {
            System.out.println("add dc");
            return ResponseEntity.status(HttpStatus.CREATED).body(addedVoucher);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/view")
    public String showVoucherForm(Model model) {
        List<VoucherDTO> vouchers = voucherService.getVouchersFromServer();
        model.addAttribute("vouchers", vouchers);
        return "page/Admin/ThemMoiKhuyenMai";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVoucherFromClient(@PathVariable int id) {
        voucherService.deleteVoucher(id);
        return ResponseEntity.ok("Voucher deleted from client");
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherDTO> getVoucherById(@PathVariable int id) {
        VoucherDTO voucher = voucherService.getVoucherById(id);
        return ResponseEntity.ok(voucher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoucherDTO> updateVoucher(@PathVariable int id, @RequestBody VoucherDTO voucher) {
        VoucherDTO updatedVoucher = voucherService.updateVoucher(id, voucher);
        return ResponseEntity.ok(updatedVoucher);
    }



}

