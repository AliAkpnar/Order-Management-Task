package com.migros.ordermanagement.advice.exception;

import org.springframework.http.HttpStatus;

import static com.migros.ordermanagement.advice.constants.ErrorCodes.MANAGEMENT;

public class ManagementAPIException extends BaseRuntimeException{
    public ManagementAPIException() {
        super(MANAGEMENT, HttpStatus.BAD_REQUEST, "Invalid JWT Features");
    }
}
