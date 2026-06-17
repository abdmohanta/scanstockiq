package com.debasish.scanstockiq.dto;

import com.debasish.scanstockiq.entity.ProductStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {

    private Long id;

    private String productName;

    private String upcCode;

    private String skuCode;

    private String category;

    private String supplierName;

    private Double costPrice;

    private Double sellingPrice;

    private Integer currentStock;

    private Integer reorderLevel;

    private ProductStatus status;
}