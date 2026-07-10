package com.debasish.scanstockiq.repository;

import com.debasish.scanstockiq.entity.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderItemRepository
        extends JpaRepository<PurchaseOrderItem, Long> {

}