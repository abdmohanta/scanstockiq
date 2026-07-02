package com.debasish.scanstockiq.service;

import com.debasish.scanstockiq.dto.DashboardResponse;
import com.debasish.scanstockiq.dto.FastMovingProductResponse;
import com.debasish.scanstockiq.dto.LowStockResponse;

import java.util.List;

public interface DashboardService {

    DashboardResponse getDashboard();

    List<FastMovingProductResponse> getFastMovingProducts();

    List<LowStockResponse> getLowStockProducts();
}