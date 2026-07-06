package com.debasish.scanstockiq.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Builder
public class FastMovingProductResponse {

    private Long productId;

    private String productName;

    private String upcCode;

    private Integer totalSold;
}