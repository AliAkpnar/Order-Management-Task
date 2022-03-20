package com.migros.ordermanagement.service;

import com.migros.ordermanagement.model.dto.OrderDto;
import com.migros.ordermanagement.model.request.OrderRequest;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    List<OrderDto> getOrdersByCustomerId(long customerId);
    OrderDto saveOrders(long customerId, OrderRequest orderRequest);
    OrderDto updateOrder(Long customerId, Long orderId, OrderRequest orderRequest);
    void deleteOrder(Long customerId, Long orderId);
}
