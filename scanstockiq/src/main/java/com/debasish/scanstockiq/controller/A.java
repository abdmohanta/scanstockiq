package com.debasish.scanstockiq.controller;

public class A {

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
