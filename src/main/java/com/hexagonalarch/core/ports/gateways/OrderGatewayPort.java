package com.hexagonalarch.core.ports.gateways;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderGatewayPort {

    Order save(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll(OrderStatus orderStatus);

}