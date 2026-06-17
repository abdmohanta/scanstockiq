package com.debasish.scanstockiq.exception;

public class InvalidBarcodeException extends RuntimeException {

    public InvalidBarcodeException(String message) {
        super(message);
    }
}