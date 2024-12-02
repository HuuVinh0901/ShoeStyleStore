package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Receipt;
import com.shoestore.Server.repositories.ReceiptRepository;
import com.shoestore.Server.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Override
    public Receipt addReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }
}
