package com.debasish.scanstockiq.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierRequest {

    @NotBlank
    private String supplierName;

    @NotBlank
    private String supplierCode;

    @Email
    private String email;

    private String phoneNumber;

    private String address;

}