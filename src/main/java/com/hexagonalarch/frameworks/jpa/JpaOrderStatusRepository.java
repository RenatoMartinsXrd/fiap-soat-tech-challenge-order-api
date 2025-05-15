package com.hexagonalarch.frameworks.jpa;

import com.hexagonalarch.frameworks.jpa.entity.OrderStatusEntity;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaOrderStatusRepository extends JpaRepository<OrderStatusEntity, Long> {

    Optional<OrderStatusEntity> findByStatus(OrderStatus status);
}
