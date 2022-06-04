package com.migros.ordermanagement.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
