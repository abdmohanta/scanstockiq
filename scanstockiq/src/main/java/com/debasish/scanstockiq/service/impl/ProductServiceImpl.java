package com.debasish.scanstockiq.service.impl;

import com.debasish.scanstockiq.dto.ProductRequest;
import com.debasish.scanstockiq.dto.ProductResponse;
import com.debasish.scanstockiq.entity.Product;
import com.debasish.scanstockiq.entity.ProductStatus;
import com.debasish.scanstockiq.exception.DuplicateUpcException;
import com.debasish.scanstockiq.exception.ProductNotFoundException;
import com.debasish.scanstockiq.repository.ProductRepository;
import com.debasish.scanstockiq.service.ProductService;
import com.debasish.scanstockiq.util.BarcodeValidationUtil;
import com.debasish.scanstockiq.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        BarcodeValidationUtil.validate(
                request.getUpcCode());

        if (productRepository.existsByUpcCode(
                request.getUpcCode())) {

            throw new DuplicateUpcException(
                    "UPC already exists");
        }

        Product product = new Product();

        product.setProductName(request.getProductName());
        product.setUpcCode(request.getUpcCode());
        product.setSkuCode(request.getSkuCode());
        product.setCategory(request.getCategory());
        product.setSupplierName(request.getSupplierName());
        product.setCostPrice(request.getCostPrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setCurrentStock(request.getCurrentStock());
        product.setReorderLevel(request.getReorderLevel());
        product.setStatus(ProductStatus.ACTIVE);

        product = productRepository.save(product);

        return ProductMapper.toResponse(product);
    }

    @Override
    public ProductResponse getProduct(Long id) {

        Product product =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found"));

        return ProductMapper.toResponse(product);
    }

    @Override
    public ProductResponse getProductByUpc(String upcCode) {

        Product product =
                productRepository.findByUpcCode(upcCode)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found"));

        return ProductMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteProduct(Long id) {

        Product product =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found"));

        productRepository.delete(product);
    }
}