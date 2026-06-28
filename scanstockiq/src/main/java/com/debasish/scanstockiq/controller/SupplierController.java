package com.debasish.scanstockiq.controller;

import com.debasish.scanstockiq.dto.SuccessResponse;
import com.debasish.scanstockiq.dto.SupplierRequest;
import com.debasish.scanstockiq.dto.SupplierResponse;
import com.debasish.scanstockiq.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public SuccessResponse<SupplierResponse>
    createSupplier(
            @Valid @RequestBody SupplierRequest request) {

        return SuccessResponse.<SupplierResponse>builder()
                .success(true)
                .message("Supplier created successfully")
                .data(
                        supplierService.createSupplier(
                                request))
                .build();
    }

    @GetMapping("/{id}")
    public SuccessResponse<SupplierResponse>
    getSupplier(@PathVariable Long id) {

        return SuccessResponse.<SupplierResponse>builder()
                .success(true)
                .message("Supplier fetched successfully")
                .data(
                        supplierService.getSupplier(id))
                .build();
    }

    @GetMapping
    public SuccessResponse<List<SupplierResponse>>
    getAllSuppliers() {

        return SuccessResponse
                .<List<SupplierResponse>>builder()
                .success(true)
                .message("Suppliers fetched successfully")
                .data(
                        supplierService.getAllSuppliers())
                .build();
    }

}