package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.VoucherDTO;
import com.shoestore.client.dto.response.VoucherResponseDTO;
import com.shoestore.client.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private RestTemplate restTemplate;
    private static final String SERVER_URL = "http://localhost:8080/vouchers";
    @Override
    public List<VoucherDTO> searchVouchers(LocalDate startDate, LocalDate endDate) {
        String url = String.format("%s?startDate=%s&endDate=%s", SERVER_URL+"/search",
                startDate != null ? startDate : "",
                endDate != null ? endDate : "");

         VoucherDTO[] response = restTemplate.getForObject(url, VoucherDTO[].class);
        return Arrays.asList(response);
    }
    @Override
    public VoucherDTO addVoucher(VoucherDTO voucher) {
        // Gửi request đến API server
        String url = SERVER_URL + "/add";
        return restTemplate.postForObject(url, voucher, VoucherDTO.class);
    }

    public List<VoucherDTO> getVouchersFromServer() {
        String apiUrl = "http://localhost:8080/vouchers";    // URL API trả về đối tượng chứa mảng "vouchers"
        // Sử dụng ResponseEntity để lấy đối tượng JSON chứa danh sách vouchers
        ResponseEntity<VoucherResponseDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null,
                VoucherResponseDTO.class
        );

        // Truyền trả về danh sách vouchers từ đối tượng JSON
        return response.getBody().getVouchers();
    }



    @Override
    public void deleteVoucher(int voucherID) {
        String apiUrl = SERVER_URL+"/"+ voucherID;
        restTemplate.delete(apiUrl);
    }

    @Override
    public VoucherDTO getVoucherById(int id) {
        return restTemplate.getForObject(SERVER_URL +"/"+ id, VoucherDTO.class);
    }

    @Override
    public VoucherDTO updateVoucher(int id, VoucherDTO voucher) {
        HttpEntity<VoucherDTO> request = new HttpEntity<>(voucher);
        ResponseEntity<VoucherDTO> response = restTemplate.exchange(
                SERVER_URL +"/" + id,
                HttpMethod.PUT,
                request,
                VoucherDTO.class
        );
        return response.getBody();
    }



}
