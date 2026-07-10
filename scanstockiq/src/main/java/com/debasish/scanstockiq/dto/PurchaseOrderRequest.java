package com.debasish.scanstockiq.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderRequest {

    @NotBlank
    private String purchaseOrderNumber;

    private Long supplierId;

    private Long productId;

    @Min(1)
    private Integer quantity;

    private Double unitPrice;
}