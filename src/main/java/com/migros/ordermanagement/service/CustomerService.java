package com.migros.ordermanagement.service;

import com.migros.ordermanagement.model.dto.CustomerDto;
import com.migros.ordermanagement.model.request.CustomerRequest;
import com.migros.ordermanagement.model.response.CustomerPageResponse;

public interface CustomerService {
    CustomerPageResponse getAllCustomer(int pageNo, int pageSize, String sortBy, String sortDir);
    CustomerDto saveCustomer(CustomerRequest customer);
    CustomerDto getCustomerById(long id);
    CustomerDto updateCustomer(CustomerRequest customerRequest, long id);
}
