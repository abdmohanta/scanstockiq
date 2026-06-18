package com.debasish.scanstockiq.util;

import com.debasish.scanstockiq.dto.ProductResponse;
import com.debasish.scanstockiq.entity.Product;

public final class ProductMapper {

    private ProductMapper() {
    }

    public static ProductResponse toResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .upcCode(product.getUpcCode())
                .skuCode(product.getSkuCode())
                .category(product.getCategory())
                .supplierName(product.getSupplierName())
                .costPrice(product.getCostPrice())
                .sellingPrice(product.getSellingPrice())
                .currentStock(product.getCurrentStock())
                .reorderLevel(product.getReorderLevel())
                .status(product.getStatus())
                .build();
    }
}