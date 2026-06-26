package com.debasish.scanstockiq.service;

import com.debasish.scanstockiq.dto.InventoryHistoryResponse;
import com.debasish.scanstockiq.dto.ReturnRequest;
import com.debasish.scanstockiq.dto.StockOperationRequest;

import java.util.List;

public interface InventoryService {

    void stockIn(StockOperationRequest request);

    void sale(StockOperationRequest request);

    void damage(StockOperationRequest request);

    void manualAdjustment(StockOperationRequest request);

    void processReturn(ReturnRequest request);

    List<InventoryHistoryResponse> getInventoryHistory(String upcCode);
}