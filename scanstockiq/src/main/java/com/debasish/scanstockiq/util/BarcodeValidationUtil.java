package com.debasish.scanstockiq.util;

import com.debasish.scanstockiq.exception.InvalidBarcodeException;

public final class BarcodeValidationUtil {

    private BarcodeValidationUtil() {
    }

    public static void validate(String barcode) {

        if (barcode == null || barcode.isBlank()) {
            throw new InvalidBarcodeException(
                    "Barcode cannot be empty");
        }

        if (!barcode.matches("\\d+")) {
            throw new InvalidBarcodeException(
                    "Barcode must contain only digits");
        }

        if (barcode.length() != 12) {
            throw new InvalidBarcodeException(
                    "UPC barcode must contain 12 digits");
        }
    }
}