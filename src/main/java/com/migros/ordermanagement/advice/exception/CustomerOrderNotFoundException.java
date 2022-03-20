package com.migros.ordermanagement.advice.exception;

import org.springframework.http.HttpStatus;

import static com.migros.ordermanagement.advice.constants.ErrorCodes.ORDER_DOES_NOT_EXIST_BELONG_TO_CUSTOMER;

public class CustomerOrderNotFoundException extends BaseRuntimeException {
    public CustomerOrderNotFoundException() {
        super(ORDER_DOES_NOT_EXIST_BELONG_TO_CUSTOMER, HttpStatus.NOT_FOUND, "Order does not exist belong to customer");
    }
}
