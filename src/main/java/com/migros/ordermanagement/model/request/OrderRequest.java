package com.migros.ordermanagement.model.request;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @DecimalMin(value = "0.01")
    private BigDecimal orderPrice;
    @Min(value = 1, message = "Order quantity must have at least one!")
    private int quantity;
    private String orderName;
    @Future(message = "Must be a date in the present or in the feature!")
    private Instant orderDate;
}
