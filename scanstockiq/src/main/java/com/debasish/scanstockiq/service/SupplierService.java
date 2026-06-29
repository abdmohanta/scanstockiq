package com.debasish.scanstockiq.service;

import com.debasish.scanstockiq.dto.SupplierRequest;
import com.debasish.scanstockiq.dto.SupplierResponse;

import java.util.List;

public interface SupplierService {

    SupplierResponse createSupplier(
            SupplierRequest request);

    SupplierResponse getSupplier(Long id);

    List<SupplierResponse> getAllSuppliers();

}