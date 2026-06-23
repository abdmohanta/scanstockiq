package com.debasish.scanstockiq.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockOperationRequest {

    private String upcCode;

    private Integer quantity;

    private String remarks;

}

