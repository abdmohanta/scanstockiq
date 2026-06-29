package com.debasish.scanstockiq.util;

import com.debasish.scanstockiq.dto.SupplierResponse;
import com.debasish.scanstockiq.entity.Supplier;

public final class SupplierMapper {

    private SupplierMapper() {
    }

    public static SupplierResponse toResponse(
            Supplier supplier) {

        return SupplierResponse.builder()
                .id(supplier.getId())
                .supplierName(supplier.getSupplierName())
                .supplierCode(supplier.getSupplierCode())
                .email(supplier.getEmail())
                .phoneNumber(supplier.getPhoneNumber())
                .address(supplier.getAddress())
                .active(supplier.getActive())
                .build();
    }
}