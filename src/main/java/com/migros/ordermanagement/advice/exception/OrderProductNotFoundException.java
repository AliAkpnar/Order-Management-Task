package com.migros.ordermanagement.advice.exception;

import org.springframework.http.HttpStatus;

import static com.migros.ordermanagement.advice.constants.ErrorCodes.PRODUCT_DOES_NOT_EXIST_BELONG_TO_ORDER;

public class OrderProductNotFoundException extends BaseRuntimeException {
    public OrderProductNotFoundException() {
        super(PRODUCT_DOES_NOT_EXIST_BELONG_TO_ORDER, HttpStatus.NOT_FOUND, "Product does not exist belong to order");
    }
}
