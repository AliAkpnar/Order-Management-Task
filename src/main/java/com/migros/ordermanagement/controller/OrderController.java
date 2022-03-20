package com.migros.ordermanagement.controller;

import com.migros.ordermanagement.model.dto.OrderDto;
import com.migros.ordermanagement.model.request.OrderRequest;
import com.migros.ordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerId(@PathVariable(value = "customerId") long customerId){
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    @PostMapping("/save/{customerId}")
    public ResponseEntity<OrderDto> saveOrder(@PathVariable(value = "customerId") long customerId,@Valid @RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.saveOrders(customerId, orderRequest));
    }

    @PutMapping("/{customerId}/orders/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable(value = "customerId") Long customerId,
                                                    @PathVariable(value = "id")  Long orderId,
                                                    @RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.updateOrder(customerId, orderId, orderRequest));
    }

    @DeleteMapping("/{customerId}/orders/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable(value = "customerId") Long customerId,
                                                @PathVariable(value = "id") Long orderId){
        orderService.deleteOrder(customerId, orderId);
        return ResponseEntity.ok("Order is cancelled successfully");
    }
}
