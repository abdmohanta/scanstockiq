package com.debasish.scanstockiq.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FastMovingProductResponse {

    private Long productId;

    private String productName;

    private String upcCode;

    private Long totalSold;

    public FastMovingProductResponse(Long productId,
                                     String productName,
                                     String upcCode,
                                     Long totalSold) {

        this.productId = productId;
        this.productName = productName;
        this.upcCode = upcCode;
        this.totalSold = totalSold;
    }

    public static Object builder() {
        return null;
    }
}