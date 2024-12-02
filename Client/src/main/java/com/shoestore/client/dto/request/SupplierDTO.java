package com.shoestore.client.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SupplierDTO {
    private int supplierID;
    private String supplierName;
    private String address;
    private List<String> phoneNumbers;
}
