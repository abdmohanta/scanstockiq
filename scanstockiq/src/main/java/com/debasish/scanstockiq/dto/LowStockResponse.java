package com.debasish.scanstockiq.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LowStockResponse {

    private Long productId;

    private String productName;

    private String upcCode;

    private Integer currentStock;

    private Integer reorderLevel;

    public LowStockResponse(Long productId,
                            String productName,
                            String upcCode,
                            Integer currentStock,
                            Integer reorderLevel) {

        this.productId = productId;
        this.productName = productName;
        this.upcCode = upcCode;
        this.currentStock = currentStock;
        this.reorderLevel = reorderLevel;
    }
}