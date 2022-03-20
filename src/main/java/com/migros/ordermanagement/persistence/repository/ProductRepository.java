package com.migros.ordermanagement.persistence.repository;

import com.migros.ordermanagement.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByOrderId(final long orderId);
}
