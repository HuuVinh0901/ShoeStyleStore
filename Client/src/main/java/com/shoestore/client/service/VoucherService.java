package com.shoestore.client.service;

import com.shoestore.client.dto.request.VoucherDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface VoucherService {
    public List<VoucherDTO> getVouchersFromServer() ;
    public VoucherDTO addVoucher(VoucherDTO voucher);
    public void deleteVoucher(int voucherID);
    public VoucherDTO updateVoucher(int id, VoucherDTO voucher);
    public VoucherDTO getVoucherById(int id);
    public List<VoucherDTO> searchVouchers(LocalDate startDate, LocalDate endDate);
}
