package com.debasish.scanstockiq.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ssi_supplier")
public class Supplier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String supplierName;

    @Column(unique = true)
    private String supplierCode;

    private String email;

    private String phoneNumber;

    private String address;

    private Boolean active;
}