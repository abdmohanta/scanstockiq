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
    private final InventoryTransactionRepository inventoryTransactionRepository;

    @Override
    public DashboardResponse getDashboard() {

        List<Product> products = productRepository.findAll();

        double inventoryValue = products.stream()
                .mapToDouble(product ->
                        product.getCurrentStock() * product.getCostPrice())
                .sum();

        long lowStockProducts = products.stream()
                .filter(product ->
                        product.getCurrentStock() <= product.getReorderLevel())
                .count();

        return DashboardResponse.builder()
                .totalProducts(productRepository.count())
                .activeProducts(productRepository.countByStatus(ProductStatus.ACTIVE))
                .outOfStockProducts(productRepository.countByStatus(ProductStatus.OUT_OF_STOCK))
                .lowStockProducts(lowStockProducts)
                .totalSuppliers(supplierRepository.count())
                .inventoryTransactions(inventoryTransactionRepository.count())
                .inventoryValue(inventoryValue)
                .build();
    }

    @Override
    public List<FastMovingProductResponse> getFastMovingProducts() {

        Map<Product, Integer> salesMap = new HashMap<>();

        List<InventoryTransaction> transactions =
                inventoryTransactionRepository.findAll();

        for (InventoryTransaction transaction : transactions) {

            if (transaction.getTransactionType() == InventoryTransactionType.SALE) {

                Product product = transaction.getProduct();

                salesMap.merge(
                        product,
                        transaction.getQuantity(),
                        Integer::sum
                );
            }
        }

        return salesMap.entrySet()
                .stream()
                .sorted((entry1, entry2) ->
                        entry2.getValue().compareTo(entry1.getValue()))
                .limit(10)
                .map(entry ->
                        FastMovingProductResponse.builder()
                                .productId(entry.getKey().getId())
                                .productName(entry.getKey().getProductName())
                                .upcCode(entry.getKey().getUpcCode())
                                .totalSold(entry.getValue())
                                .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<LowStockResponse> getLowStockProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .filter(product ->
                        product.getCurrentStock() <= product.getReorderLevel())
                .map(product ->
                        LowStockResponse.builder()
                                .productId(product.getId())
                                .productName(product.getProductName())
                                .upcCode(product.getUpcCode())
                                .currentStock(product.getCurrentStock())
                                .reorderLevel(product.getReorderLevel())
                                .build())
                .collect(Collectors.toList());
    }
}