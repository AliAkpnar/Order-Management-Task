package com.migros.ordermanagement.persistence.repository;

import com.migros.ordermanagement.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByCustomerEntityId(final long customerId);
}
