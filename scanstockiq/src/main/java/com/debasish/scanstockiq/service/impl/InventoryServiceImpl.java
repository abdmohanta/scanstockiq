package com.debasish.scanstockiq.service.impl;

import com.debasish.scanstockiq.dto.ReturnRequest;
import com.debasish.scanstockiq.dto.StockOperationRequest;
import com.debasish.scanstockiq.entity.*;
import com.debasish.scanstockiq.exception.InsufficientStockException;
import com.debasish.scanstockiq.exception.ProductNotFoundException;
import com.debasish.scanstockiq.repository.InventoryTransactionRepository;
import com.debasish.scanstockiq.repository.ProductRepository;
import com.debasish.scanstockiq.repository.ReturnInventoryRepository;
import com.debasish.scanstockiq.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;
    private final ReturnInventoryRepository returnInventoryRepository;