package com.debasish.scanstockiq.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ssi_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @Column(unique = true, nullable = false)
    private String upcCode;

    private String skuCode;

    private String category;

    private String supplierName;

    private Double costPrice;

    private Double sellingPrice;

    private Integer currentStock;

    private Integer reorderLevel;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}
