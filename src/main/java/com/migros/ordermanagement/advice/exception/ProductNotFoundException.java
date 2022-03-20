package com.migros.ordermanagement.advice.exception;

import org.springframework.http.HttpStatus;

import static com.migros.ordermanagement.advice.constants.ErrorCodes.PRODUCT_NOT_FOUND;

public class ProductNotFoundException extends BaseRuntimeException {
    public ProductNotFoundException() {
        super(PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND, "Product Not Found!");
    }
}
