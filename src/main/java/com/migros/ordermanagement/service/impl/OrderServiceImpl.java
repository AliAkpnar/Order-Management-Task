package com.migros.ordermanagement.service.impl;

import com.migros.ordermanagement.advice.exception.*;
import com.migros.ordermanagement.converter.OrderEntityConverter;
import com.migros.ordermanagement.model.dto.*;
import com.migros.ordermanagement.persistence.entity.CustomerEntity;
import com.migros.ordermanagement.persistence.entity.OrderEntity;
import com.migros.ordermanagement.persistence.entity.ProductEntity;
import com.migros.ordermanagement.persistence.repository.CustomerRepository;
import com.migros.ordermanagement.persistence.repository.OrderRepository;
import com.migros.ordermanagement.model.request.OrderRequest;
import com.migros.ordermanagement.persistence.repository.ProductRepository;
import com.migros.ordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities.stream().map(OrderEntityConverter::toMapOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(long customerId) {
        List<OrderEntity> orderEntities = orderRepository.findByCustomerEntityId(customerId);
        return orderEntities.stream()
                .map(OrderEntityConverter::toMapOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto saveOrders(long customerId, OrderRequest orderRequest) {
        OrderEntity orderEntity = mapToEntity(orderRequest);
        CustomerEntity customerEntity = customerRepository
                .findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        List<ProductEntity> productEntity = productRepository.findAll();
        orderEntity.setCustomerEntity(customerEntity);
        orderEntity.setProducts(productEntity);
        if (orderRequest.getQuantity() < 1){
            throw new OrderQuantityException();
        }
        orderRepository.save(orderEntity);
        log.info("Order is saved : {}", orderEntity.getId());
        return OrderEntityConverter.toMapOrderDto(orderEntity);
    }

    @Override
    public OrderDto updateOrder(Long customerId, Long orderId, OrderRequest orderRequest) {
        OrderEntity orderEntity = getOrder(customerId, orderId);
        orderEntity.setOrderPrice(orderRequest.getOrderPrice());
        orderEntity.setOrderName(orderRequest.getOrderName());
        orderEntity.setOrderQuantity(orderRequest.getQuantity());
        OrderEntity updatedOrder = orderRepository.save(orderEntity);
        return OrderEntityConverter.toMapOrderDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Long customerId, Long orderId) {
        OrderEntity order = getOrder(customerId, orderId);
        orderRepository.delete(order);
    }

    private OrderEntity getOrder(long customerId, long orderId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Optional<OrderEntity> orderEntity = Optional.ofNullable(orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new));
        orderEntity
                .map(OrderEntity::getCustomerEntity)
                .map(CustomerEntity::getId)
                .filter(e -> e.equals(customerEntity.getId()))
                .orElseThrow(CustomerOrderNotFoundException::new);
        return orderEntity.get();
    }

    private OrderEntity mapToEntity(OrderRequest orderRequest) {
         return modelMapper.map(orderRequest, OrderEntity.class);
    }
}
