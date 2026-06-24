package com.debasish.scanstockiq.controller;

import com.debasish.scanstockiq.dto.InventoryHistoryResponse;
import com.debasish.scanstockiq.dto.SuccessResponse;
import com.debasish.scanstockiq.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final Inventoryservice inventoryService;

    @GetMapping("/history/{upcCode}")
    public SuccessResponse<List<InventoryHistoryResponse>>
    getInventoryHistory(
            @PathVariable String upcCode) {

        return SuccessResponse
                .<List<InventoryHistoryResponse>>builder()
                .success(true)
                .message("Inventory history fetched successfully")
                .data(
                        inventoryService.getInventoryHistory(
                                upcCode))
                .build();
    }
}
