package com.migros.ordermanagement.controller;

import com.migros.ordermanagement.model.dto.CustomerDto;
import com.migros.ordermanagement.model.request.CustomerRequest;
import com.migros.ordermanagement.model.response.CustomerPageResponse;
import com.migros.ordermanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.migros.ordermanagement.utils.PageConstants.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public CustomerPageResponse getAllCustomers(
            @RequestParam(value = "pageNo" , defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return customerService.getAllCustomer(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping("/save")
    public CustomerDto saveCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        return customerService.saveCustomer(customerRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerRequest customerRequest,
                                                      @PathVariable(name = "id") long id){
        CustomerDto postResponse = customerService.updateCustomer(customerRequest, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
}
