package com.migros.ordermanagement.advice.exception;

import org.springframework.http.HttpStatus;

import static com.migros.ordermanagement.advice.constants.ErrorCodes.QUANTITY_MIN_ONE;

public class OrderQuantityException extends BaseRuntimeException {
    public OrderQuantityException() {
        super(QUANTITY_MIN_ONE, HttpStatus.BAD_REQUEST, "Quantity must be at least 1");
    }
}
