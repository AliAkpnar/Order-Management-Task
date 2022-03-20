package com.migros.ordermanagement.advice.exception;

import org.springframework.http.HttpStatus;

import static com.migros.ordermanagement.advice.constants.ErrorCodes.CUSTOMER_ALREADY_EXIST;

public class CustomerAlreadyExistException extends BaseRuntimeException {
    public CustomerAlreadyExistException() {
        super(CUSTOMER_ALREADY_EXIST, HttpStatus.CONFLICT, "Customer already exists!");
    }
}
