package com.migros.ordermanagement.converter;

import com.migros.ordermanagement.model.dto.CustomerDto;
import com.migros.ordermanagement.persistence.entity.CustomerEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerEntityConverter {
    public static CustomerDto toMapCustomerDto(final CustomerEntity customerEntity){
        return  CustomerDto.builder()
                .customerId(customerEntity.getId())
                .firstname(customerEntity.getFirstname())
                .lastname(customerEntity.getLastname())
                .email(customerEntity.getEmail())
                .phoneNumber(customerEntity.getPhoneNumber())
                .orders(customerEntity.getOrders()
                        .stream()
                        .map(OrderEntityConverter::toMapOrderDto)
                        .collect(Collectors.toSet()))
                .build();
    }
}
