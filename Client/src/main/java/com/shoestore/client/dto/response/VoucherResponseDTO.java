package com.shoestore.client.dto.response;

import com.shoestore.client.dto.request.VoucherDTO;

import java.util.List;

public class VoucherResponseDTO {
    private int totalItems;
    private int totalPages;
    private List<VoucherDTO> vouchers;

    // Getter và setter
    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<VoucherDTO> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<VoucherDTO> vouchers) {
        this.vouchers = vouchers;
    }
}

