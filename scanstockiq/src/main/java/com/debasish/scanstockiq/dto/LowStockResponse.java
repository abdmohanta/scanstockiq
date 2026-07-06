package com.debasish.scanstockiq.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LowStockResponse {

    private Long productId;

    private String productName;

    private String upcCode;

    private Integer currentStock;

    private Integer reorderLevel;

}