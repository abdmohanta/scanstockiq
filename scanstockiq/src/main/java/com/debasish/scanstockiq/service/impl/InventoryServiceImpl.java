package com.debasish.scanstockiq.service.impl;

import com.debasish.scanstockiq.dto.InventoryHistoryResponse;
import com.debasish.scanstockiq.dto.ReturnRequest;
import com.debasish.scanstockiq.dto.StockOperationRequest;
import com.debasish.scanstockiq.entity.InventoryTransaction;
import com.debasish.scanstockiq.entity.InventoryTransactionType;
import com.debasish.scanstockiq.entity.Product;
import com.debasish.scanstockiq.entity.ProductStatus;
import com.debasish.scanstockiq.entity.ReturnCondition;
import com.debasish.scanstockiq.entity.ReturnInventory;
import com.debasish.scanstockiq.exception.InsufficientStockException;
import com.debasish.scanstockiq.exception.ProductNotFoundException;
import com.debasish.scanstockiq.repository.InventoryTransactionRepository;
import com.debasish.scanstockiq.repository.ProductRepository;
import com.debasish.scanstockiq.repository.ReturnInventoryRepository;
import com.debasish.scanstockiq.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void damage(StockOperationRequest request) {
        Product product = getProduct(request.getUpcCode());

        int before = product.getCurrentStock();

        if (before < request.getQuantity()) {
            throw new InsufficientStockException("Available stock: " + before);
        }

        int after = before - request.getQuantity();

        product.setCurrentStock(after);
        if (after == 0) {
            product.setStatus(ProductStatus.OUT_OF_STOCK);
        }

        productRepository.save(product);

        saveTransaction(product, InventoryTransactionType.DAMAGE, request.getQuantity(), before, after, request.getRemarks());
    }

    @Override
    public void manualAdjustment(StockOperationRequest request) {
        Product product = getProduct(request.getUpcCode());

        int before = product.getCurrentStock();
        int after = request.getQuantity();

        product.setCurrentStock(after);
        if (after == 0) {
            product.setStatus(ProductStatus.OUT_OF_STOCK);
        } else {
            product.setStatus(ProductStatus.AVAILABLE);
        }

        productRepository.save(product);

        saveTransaction(product, InventoryTransactionType.MANUAL_ADJUSTMENT, Math.abs(after - before), before, after, request.getRemarks());
    }

    @Override
    public void processReturn(ReturnRequest request) {
        Product product = getProduct(request.getUpcCode());

        int before = product.getCurrentStock();
        int after = before + request.getQuantity();

        product.setCurrentStock(after);
        product.setStatus(ProductStatus.AVAILABLE);

        productRepository.save(product);

        // save return record
        ReturnInventory ret = new ReturnInventory();
        ret.setProduct(product);
        ret.setQuantity(request.getQuantity());
        ret.setRemarks(request.getRemarks());
        ret.setReturnCondition(request.getReturnCondition() != null ? request.getReturnCondition() : ReturnCondition.GOOD);
        ret.setReturnedAt(LocalDateTime.now());

        returnInventoryRepository.save(ret);

        saveTransaction(product, InventoryTransactionType.RETURN, request.getQuantity(), before, after, request.getRemarks());
    }

    @Override
    public List<InventoryHistoryResponse> getInventoryHistory(
            String upcCode) {

        Product product = getProduct(upcCode);

        return inventoryTransactionRepository
                .findByProductOrderByTransactionDateDesc(product)
                .stream()
                .map(transaction ->
                        InventoryHistoryResponse.builder()
                                .transactionId(transaction.getId())
                                .productName(product.getProductName())
                                .upcCode(product.getUpcCode())
                                .transactionType(transaction.getTransactionType())
                                .quantity(transaction.getQuantity())
                                .stockBefore(transaction.getStockBefore())
                                .stockAfter(transaction.getStockAfter())
                                .remarks(transaction.getRemarks())
                                .createdAt(transaction.getTransactionDate())
                                .build())
                .collect(Collectors.toList());
    }

    private void saveTransaction(Product product,
                                 InventoryTransactionType type,
                                 int quantity,
                                 int before,
                                 int after,
                                 String remarks) {

        InventoryTransaction tx = new InventoryTransaction();
        tx.setProduct(product);
        tx.setTransactionType(type);
        tx.setQuantity(quantity);
        tx.setStockBefore(before);
        tx.setStockAfter(after);
        tx.setRemarks(remarks);
        tx.setTransactionDate(LocalDateTime.now());

        inventoryTransactionRepository.save(tx);
    }

}