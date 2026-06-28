package com.debasish.scanstockiq.service.impl;

import com.debasish.scanstockiq.dto.SupplierRequest;
import com.debasish.scanstockiq.dto.SupplierResponse;
import com.debasish.scanstockiq.entity.Supplier;
import com.debasish.scanstockiq.repository.SupplierRepository;
import com.debasish.scanstockiq.service.SupplierService;
import com.debasish.scanstockiq.util.SupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl
        implements SupplierService {

    private final SupplierRepository repository;

    @Override
    public SupplierResponse createSupplier(
            SupplierRequest request) {

        Supplier supplier = new Supplier();

        supplier.setSupplierName(
                request.getSupplierName());

        supplier.setSupplierCode(
                request.getSupplierCode());

        supplier.setEmail(
                request.getEmail());

        supplier.setPhoneNumber(
                request.getPhoneNumber());

        supplier.setAddress(
                request.getAddress());

        supplier.setActive(true);

        supplier = repository.save(supplier);

        return SupplierMapper.toResponse(
                supplier);
    }

    @Override
    public SupplierResponse getSupplier(Long id) {

        return SupplierMapper.toResponse(
                repository.findById(id).orElseThrow());
    }

    @Override
    public List<SupplierResponse> getAllSuppliers() {

        return repository.findAll()
                .stream()
                .map(SupplierMapper::toResponse)
                .toList();
    }

}