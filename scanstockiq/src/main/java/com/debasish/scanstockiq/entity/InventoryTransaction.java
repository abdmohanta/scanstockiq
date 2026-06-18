package com.debasish.scanstockiq.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ssi_inventory_transaction")
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private InventoryTransactionType transactionType;

    private Integer quantity;

    private Integer stockBefore;

    private Integer stockAfter;

    private String remarks;

    private LocalDateTime transactionDate;
}