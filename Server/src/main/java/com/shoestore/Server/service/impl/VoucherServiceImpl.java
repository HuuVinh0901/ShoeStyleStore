package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Voucher;
import com.shoestore.Server.repositories.VoucherRepository;
import com.shoestore.Server.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;

    public VoucherServiceImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    public List<Voucher> findVoucherByCodeOrDate(LocalDate startDate, LocalDate endDate) {
        // Nếu cả hai ngày đều null, trả về tất cả các voucher từ cơ sở dữ liệu
        if (startDate == null && endDate == null) {
            return voucherRepository.findAll();  // Trả về tất cả voucher từ cơ sở dữ liệu
        }

        // Nếu có ngày bắt đầu và ngày kết thúc, tìm các voucher trong phạm vi ngày
        return voucherRepository.findByStartDateBeforeAndEndDateAfter(startDate, endDate);
    }

    @Override
    public Voucher updateVoucher(int id, Voucher voucher) {
        // Kiểm tra xem voucher có tồn tại không
        Optional<Voucher> existingVoucher = voucherRepository.findById(id);
        if (existingVoucher.isPresent()) {
            Voucher entityVoucher = existingVoucher.get();

            // Cập nhật các trường dữ liệu
            entityVoucher.setName(voucher.getName());
            entityVoucher.setDescription(voucher.getDescription());
            entityVoucher.setStartDate(voucher.getStartDate());
            entityVoucher.setEndDate(voucher.getEndDate());
            entityVoucher.setDiscountType(voucher.getDiscountType());
            entityVoucher.setDiscountValue(voucher.getDiscountValue());
            entityVoucher.setMinValueOrder(voucher.getMinValueOrder());

            // Cập nhật trạng thái
            LocalDate today = LocalDate.now();
            if (voucher.getStartDate().isAfter(today)) {
                entityVoucher.setStatus("Upcoming");
            } else if (!voucher.getStartDate().isAfter(today) && !voucher.getEndDate().isBefore(today)) {
                entityVoucher.setStatus("Active");
            } else {
                entityVoucher.setStatus("Expired");
            }

            // Lưu lại voucher đã cập nhật
            return voucherRepository.save(entityVoucher);
        } else {
            return null;  // Trả về null nếu không tìm thấy voucher để cập nhật
        }
    }

//    @Override
//    public List<Voucher> findVoucherByNameOrCode(String keyword) {
//        return List.of();
//    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher addVoucher(Voucher voucher) {
        Voucher entityVoucher = new Voucher();

        // Set các trường cơ bản
        entityVoucher.setName(voucher.getName());
        entityVoucher.setDescription(voucher.getDescription());
        entityVoucher.setStartDate(voucher.getStartDate());
        entityVoucher.setEndDate(voucher.getEndDate());
        entityVoucher.setDiscountType(voucher.getDiscountType());
        entityVoucher.setDiscountValue(voucher.getDiscountValue());
        entityVoucher.setMinValueOrder(voucher.getMinValueOrder());

        // Tính toán trạng thái (status) dựa trên ngày
        LocalDate today = LocalDate.now();
        if (voucher.getStartDate().isAfter(today)) {
            entityVoucher.setStatus("Upcoming");
        } else if (!voucher.getStartDate().isAfter(today) && !voucher.getEndDate().isBefore(today)) {
            entityVoucher.setStatus("Active");
        } else {
            entityVoucher.setStatus("Expired");
        }

        // Lưu vào cơ sở dữ liệu
        return voucherRepository.save(entityVoucher);
    }

    @Override
    public void deleteVoucher(int voucherID) {
        voucherRepository.deleteById(voucherID);
    }

    public List<Voucher> searchVouchers(LocalDate startDate, LocalDate endDate) {
        return voucherRepository.findVouchersByDateRange(startDate, endDate);
    }





}
