package com.debasish.scanstockiq.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
public class ProductRequest {

    @NotBlank
    private String productName;

    @NotBlank
    private String upcCode;

    @NotBlank
    private String skuCode;

    @NotBlank
    private String category;

    @NotBlank
    private String supplierName;

    @NotNull
    @Positive
    private Double costPrice;

    @NotNull
    @Positive
    private Double sellingPrice;

    @NotNull
    @Min(0)
    private Integer currentStock;

    @NotNull
    @Min(1)
    private Integer reorderLevel;
}