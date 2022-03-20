package com.migros.ordermanagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.ordermanagement.advice.exception.CustomerAlreadyExistException;
import com.migros.ordermanagement.advice.exception.CustomerNotFoundException;
import com.migros.ordermanagement.converter.CustomerEntityConverter;
import com.migros.ordermanagement.model.dto.CustomerDto;
import com.migros.ordermanagement.persistence.entity.CustomerEntity;
import com.migros.ordermanagement.persistence.repository.CustomerRepository;
import com.migros.ordermanagement.model.request.CustomerRequest;
import com.migros.ordermanagement.model.response.CustomerPageResponse;
import com.migros.ordermanagement.service.CustomerService;
import com.migros.ordermanagement.service.LogService;
import com.migros.ordermanagement.utils.LogCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final LogService logService;
    private final ModelMapper modelMapper;

    @Override
    public CustomerPageResponse getAllCustomer(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<CustomerEntity> customerEntities = customerRepository.findAll(pageable);
        List<CustomerEntity> listOfCustomer = customerEntities.getContent();
        List<CustomerDto> content = listOfCustomer.stream()
                .map(CustomerEntityConverter::toMapCustomerDto)
                .collect(Collectors.toList());
        CustomerPageResponse customerPageResponse = new CustomerPageResponse();
        customerPageResponse.setContents(content);
        customerPageResponse.setPageNo(customerEntities.getNumber());
        customerPageResponse.setPageSize(customerEntities.getSize());
        customerPageResponse.setTotalElement(customerEntities.getTotalElements());
        customerPageResponse.setTotalPages(customerEntities.getTotalPages());
        customerPageResponse.setLast(customerEntities.isLast());
        return customerPageResponse;
    }

    @Override
    public CustomerDto saveCustomer(CustomerRequest customerRequest) {
        String className = OrderServiceImpl.class.getName();
        String logId = UUID.randomUUID().toString();
        ObjectMapper mapper = new ObjectMapper();
        log(logId, className , mapper , customerRequest , "Request", LogCodes.INFO);
        CustomerEntity customer = mapToEntity(customerRequest);
        if (customerRepository.existsByEmail(customerRequest.getEmail())){
            throw new CustomerAlreadyExistException();
        }
        customerRepository.save(customer);
        log.info("Customer saved : {}", customerRequest.getEmail());
        return CustomerEntityConverter.toMapCustomerDto(customer);
    }

    @Override
    public CustomerDto getCustomerById(long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        return CustomerEntityConverter.toMapCustomerDto(customerEntity);
    }

    @Override
    public CustomerDto updateCustomer(CustomerRequest customerRequest, long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        customerEntity.setFirstname(customerRequest.getFirstname());
        customerEntity.setLastname(customerRequest.getLastname());
        customerEntity.setEmail(customerRequest.getEmail());
        customerEntity.setPhoneNumber(customerRequest.getPhoneNumber());
        CustomerEntity updateCustomer = customerRepository.save(customerEntity);
        return CustomerEntityConverter.toMapCustomerDto(updateCustomer);
    }

    private CustomerEntity mapToEntity(CustomerRequest customerRequest) {
        return modelMapper.map(customerRequest, CustomerEntity.class);
    }

    private void log(String logId, String className, ObjectMapper mapper, Object object , String message , int logCode) {
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(object);
            logService.log(logId, className, message, jsonString , logCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
