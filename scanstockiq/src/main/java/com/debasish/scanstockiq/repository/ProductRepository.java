package com.debasish.scanstockiq.repository;

import com.debasish.scanstockiq.dto.FastMovingProductResponse;
import com.debasish.scanstockiq.dto.LowStockResponse;
import com.debasish.scanstockiq.entity.Product;
import com.debasish.scanstockiq.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByUpcCode(String upcCode);

    Optional<Product> findBySkuCode(String skuCode);

    boolean existsByUpcCode(String upcCode);

    long countByStatus(ProductStatus status);

    List<Product> findByCurrentStockLessThanEqual(Integer stock);

    @Query("""
       SELECT new com.debasish.scanstockiq.dto.LowStockResponse(
            p.id,
            p.productName,
            p.upcCode,
            p.currentStock,
            p.reorderLevel
       )
       FROM Product p
       WHERE p.currentStock <= p.reorderLevel
       ORDER BY p.currentStock ASC
       """)
    List<LowStockResponse> getLowStockProducts();

    @Query("""
       SELECT new com.debasish.scanstockiq.dto.FastMovingProductResponse(

            t.product.id,

            t.product.productName,

            t.product.upcCode,

            SUM(t.quantity)

       )

       FROM InventoryTransaction t

       WHERE t.transactionType='SALE'

       GROUP BY
            t.product.id,
            t.product.productName,
            t.product.upcCode

       ORDER BY SUM(t.quantity) DESC
       """)
    List<FastMovingProductResponse> getFastMovingProducts();
}