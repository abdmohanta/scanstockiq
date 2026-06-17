package com.debasish.scanstockiq.service;

import com.debasish.scanstockiq.dto.ProductRequest;
import com.debasish.scanstockiq.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProduct(Long id);

    ProductResponse getProductByUpc(String upcCode);

    List<ProductResponse> getAllProducts();

    void deleteProduct(Long id);
}