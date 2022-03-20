package com.migros.ordermanagement.model.response;

import com.migros.ordermanagement.model.dto.CustomerDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPageResponse {
    private List<CustomerDto> contents;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;
}
