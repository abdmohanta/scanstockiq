package com.debasish.scanstockiq.controller;

import com.debasish.scanstockiq.dto.SuccessResponse;
import com.debasish.scanstockiq.dto.ReturnRequest;
import com.debasish.scanstockiq.dto.StockOperationRequest;
import com.debasish.scanstockiq.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/stock-in")
    public SuccessResponse<String> stockIn(
            @Valid @RequestBody StockOperationRequest request) {

        inventoryService.stockIn(request);

        return SuccessResponse.<String>builder()
                .success(true)
                .message("Stock added successfully")
                .data("SUCCESS")
                .build();
    }

    @PostMapping("/sale")
    public SuccessResponse<String> sale(
            @Valid @RequestBody StockOperationRequest request) {

        inventoryService.sale(request);

        return SuccessResponse.<String>builder()
                .success(true)
                .message("Sale processed successfully")
                .data("SUCCESS")
                .build();
    }

    @PostMapping("/damage")
    public SuccessResponse<String> damage(
            @Valid @RequestBody StockOperationRequest request) {

        inventoryService.damage(request);

        return SuccessResponse.<String>builder()
                .success(true)
                .message("Damage recorded successfully")
                .data("SUCCESS")
                .build();
    }

    @PostMapping("/adjustment")
    public SuccessResponse<String> adjustment(
            @Valid @RequestBody StockOperationRequest request) {

        inventoryService.manualAdjustment(request);

        return SuccessResponse.<String>builder()
                .success(true)
                .message("Inventory adjusted successfully")
                .data("SUCCESS")
                .build();
    }

    @PostMapping("/return")
    public SuccessResponse<String> processReturn(
            @Valid @RequestBody ReturnRequest request) {

        inventoryService.processReturn(request);

        return SuccessResponse.<String>builder()
                .success(true)
                .message("Return processed successfully")
                .data("SUCCESS")
                .build();
    }
}