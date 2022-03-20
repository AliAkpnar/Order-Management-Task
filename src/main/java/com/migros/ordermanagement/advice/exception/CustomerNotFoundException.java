package com.migros.ordermanagement.advice.exception;

import org.springframework.http.HttpStatus;

import static com.migros.ordermanagement.advice.constants.ErrorCodes.CUSTOMER_NOT_FOUND;

public class CustomerNotFoundException extends BaseRuntimeException {
    public CustomerNotFoundException() {
        super(CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND, "Customer Not Found!");
    }
}

