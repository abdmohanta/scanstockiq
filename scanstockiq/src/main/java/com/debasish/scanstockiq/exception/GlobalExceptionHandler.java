package com.debasish.scanstockiq.exception;

import com.debasish.scanstockiq.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            ProductNotFoundException ex) {

        ErrorResponse response =
                ErrorResponse.builder()
                        .success(false)
                        .errorCode("PRODUCT_NOT_FOUND")
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(DuplicateUpcException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUpc(
            DuplicateUpcException ex) {

        ErrorResponse response =
                ErrorResponse.builder()
                        .success(false)
                        .errorCode("DUPLICATE_UPC")
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.badRequest()
                .body(response);
    }

    @ExceptionHandler(InvalidBarcodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBarcode(
            InvalidBarcodeException ex) {

        ErrorResponse response =
                ErrorResponse.builder()
                        .success(false)
                        .errorCode("INVALID_BARCODE")
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.badRequest()
                .body(response);
    }
}