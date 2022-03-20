package com.migros.ordermanagement.model.request;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String productName;
    private String productDescription;
    @Min(value = 1, message = "Product quantity must have at least one!")
    private int productQuantity;
}
