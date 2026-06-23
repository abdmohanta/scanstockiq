package com.debasish.scanstockiq.dto;

import com.debasish.scanstockiq.entity.InventoryTransactionType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class InventoryHistoryResponse {

    private Long transactionId;

    private String productName;

    private String upcCode;

    private InventoryTransactionType transactionType;

    private Integer quantity;

    private Integer stockBefore;

    private Integer stockAfter;

    private String remarks;

    private LocalDateTime createdAt;

}

