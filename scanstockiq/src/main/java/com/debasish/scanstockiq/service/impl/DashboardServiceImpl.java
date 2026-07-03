package com.debasish.scanstockiq.service.impl;

import com.debasish.scanstockiq.dto.DashboardResponse;
import com.debasish.scanstockiq.dto.FastMovingProductResponse;
import com.debasish.scanstockiq.dto.LowStockResponse;
import com.debasish.scanstockiq.entity.InventoryTransaction;
import com.debasish.scanstockiq.entity.InventoryTransactionType;
import com.debasish.scanstockiq.entity.Product;
import com.debasish.scanstockiq.entity.ProductStatus;
import com.debasish.scanstockiq.repository.InventoryTransactionRepository;
import com.debasish.scanstockiq.repository.ProductRepository;
import com.debasish.scanstockiq.repository.SupplierRepository;
import com.debasish.scanstockiq.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final InventoryTransactionRepository transactionRepository;