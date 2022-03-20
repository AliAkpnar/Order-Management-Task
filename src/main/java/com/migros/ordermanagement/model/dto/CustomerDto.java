package com.migros.ordermanagement.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long customerId;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private Set<OrderDto> orders;
}
