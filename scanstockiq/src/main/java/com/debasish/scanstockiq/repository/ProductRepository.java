package com.debasish.scanstockiq.repository;

import com.debasish.scanstockiq.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByUpcCode(String upcCode);

    Optional<Product> findBySkuCode(String skuCode);

    boolean existsByUpcCode(String upcCode);
}