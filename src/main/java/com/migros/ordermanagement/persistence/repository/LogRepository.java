package com.migros.ordermanagement.persistence.repository;

import com.migros.ordermanagement.persistence.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Long> {
}
