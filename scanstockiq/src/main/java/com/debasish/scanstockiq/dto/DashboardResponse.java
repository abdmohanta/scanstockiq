package com.debasish.scanstockiq.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DashboardResponse {

    private Long totalProducts;

    private Long activeProducts;

    private Long outOfStockProducts;

    private Long lowStockProducts;

    private Long totalSuppliers;

    private Long inventoryTransactions;

    private Double inventoryValue;
}