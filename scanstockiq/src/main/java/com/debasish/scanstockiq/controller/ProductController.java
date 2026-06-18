package com.debasish.scanstockiq.controller;

import com.debasish.scanstockiq.dto.SuccessResponse;
import com.debasish.scanstockiq.dto.ProductRequest;
import com.debasish.scanstockiq.dto.ProductResponse;
import com.debasish.scanstockiq.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public SuccessResponse<ProductResponse> createProduct(
             @RequestBody ProductRequest request) {

        return SuccessResponse.<ProductResponse>builder()
                .success(true)
                .message("Product created successfully")
                .data(productService.createProduct(request))
                .build();
    }

    @GetMapping("/{id}")
    public SuccessResponse<ProductResponse> getProduct(
            @PathVariable Long id) {

        return SuccessResponse.<ProductResponse>builder()
                .success(true)
                .message("Product fetched successfully")
                .data(productService.getProduct(id))
                .build();
    }

    @GetMapping("/upc/{upcCode}")
    public SuccessResponse<ProductResponse> getProductByUpc(
            @PathVariable String upcCode) {

        return SuccessResponse.<ProductResponse>builder()
                .success(true)
                .message("Product fetched successfully")
                .data(productService.getProductByUpc(upcCode))
                .build();
    }

    @GetMapping
    public SuccessResponse<List<ProductResponse>> getAllProducts() {

        return SuccessResponse.<List<ProductResponse>>builder()
                .success(true)
                .message("Products fetched successfully")
                .data(productService.getAllProducts())
                .build();
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return SuccessResponse.<String>builder()
                .success(true)
                .message("Product deleted successfully")
                .data("SUCCESS")
                .build();
    }
}
