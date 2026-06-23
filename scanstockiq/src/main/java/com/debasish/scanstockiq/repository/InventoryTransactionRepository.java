package com.debasish.scanstockiq.repository;

import com.debasish.scanstockiq.entity.InventoryTransaction;
import com.debasish.scanstockiq.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {

	// find transactions for a product ordered by transaction date desc
	List<InventoryTransaction> findByProductOrderByTransactionDateDesc(Product product);

}