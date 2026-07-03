package com.debasish.scanstockiq.controller;

import com.debasish.scanstockiq.dto.SuccessResponse;
import com.debasish.scanstockiq.dto.DashboardResponse;
import com.debasish.scanstockiq.dto.FastMovingProductResponse;
import com.debasish.scanstockiq.dto.LowStockResponse;
import com.debasish.scanstockiq.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public SuccessResponse<DashboardResponse> dashboard() {

        return SuccessResponse.<DashboardResponse>builder()
                .success(true)
                .message("Dashboard loaded successfully")
                .data(dashboardService.getDashboard())
                .build();
    }

    @GetMapping("/fast-moving")
    public SuccessResponse<List<FastMovingProductResponse>>
    fastMovingProducts() {

        return SuccessResponse
                .<List<FastMovingProductResponse>>builder()
                .success(true)
                .message("Fast moving products")
                .data(
                        dashboardService
                                .getFastMovingProducts())
                .build();
    }

    @GetMapping("/low-stock")
    public SuccessResponse<List<LowStockResponse>>
    lowStockProducts() {

        return SuccessResponse
                .<List<LowStockResponse>>builder()
                .success(true)
                .message("Low stock products")
                .data(
                        dashboardService
                                .getLowStockProducts())
                .build();
    }
}