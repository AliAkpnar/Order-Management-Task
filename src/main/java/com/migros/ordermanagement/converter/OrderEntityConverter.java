package com.migros.ordermanagement.converter;
;
import com.migros.ordermanagement.model.dto.OrderDto;
import com.migros.ordermanagement.persistence.entity.OrderEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderEntityConverter {
    public static OrderDto toMapOrderDto(final OrderEntity orderEntity){
        return OrderDto.builder()
                .id(orderEntity.getId())
                .orderPrice(orderEntity.getOrderPrice())
                .orderName(orderEntity.getOrderName())
                .products(orderEntity.getProducts()
                        .stream()
                        .map(ProductEntityConverter::toMapProductDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
