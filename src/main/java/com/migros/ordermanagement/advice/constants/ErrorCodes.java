package com.migros.ordermanagement.advice.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCodes {
    public static final int CUSTOMER_ALREADY_EXIST = 101;
    public static final int QUANTITY_MIN_ONE = 102;
    public static final int CUSTOMER_NOT_FOUND = 103;
    public static final int ORDER_NOT_FOUND = 104;
    public static final int ORDER_DOES_NOT_EXIST_BELONG_TO_CUSTOMER = 105;
    public static final int PRODUCT_DOES_NOT_EXIST_BELONG_TO_ORDER = 106;
    public static final int PRODUCT_NOT_FOUND = 107;
    public static final int VALIDATION_ERROR = 108;
    public static final int MANAGEMENT = 109;
}
