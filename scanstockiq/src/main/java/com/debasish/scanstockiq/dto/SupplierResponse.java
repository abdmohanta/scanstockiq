package com.debasish.scanstockiq.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SupplierResponse {

    private Long id;

    private String supplierName;

    private String supplierCode;

    private String email;

    private String phoneNumber;

    private String address;

    private Boolean active;

}