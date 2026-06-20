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

    private Product getProduct(String upcCode) {

        return productRepository.findByUpcCode(upcCode)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found"));
    }

    @Override
    public void stockIn(StockOperationRequest request) {

        Product product = getProduct(request.getUpcCode());

        int before = product.getCurrentStock();
        int after = before + request.getQuantity();

        product.setCurrentStock(after);

        productRepository.save(product);

        saveTransaction(
                product,
                InventoryTransactionType.STOCK_IN,
                request.getQuantity(),
                before,
                after,
                request.getRemarks()
        );
    }

    @Override
    public void sale(StockOperationRequest request) {

        Product product = getProduct(request.getUpcCode());

        int before = product.getCurrentStock();

        if (before < request.getQuantity()) {

            throw new InsufficientStockException(
                    "Available stock: " + before);
        }

        int after = before - request.getQuantity();

        product.setCurrentStock(after);

        if (after == 0) {
            product.setStatus(ProductStatus.OUT_OF_STOCK);
        }

        productRepository.save(product);

        saveTransaction(
                product,
                InventoryTransactionType.SALE,
                request.getQuantity(),
                before,
                after,
                request.getRemarks()
        );
    }

}