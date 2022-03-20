package com.migros.ordermanagement.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private BigDecimal orderPrice;
    private int quantity;
    private String orderName;
    private List<ProductDto> products;
}
