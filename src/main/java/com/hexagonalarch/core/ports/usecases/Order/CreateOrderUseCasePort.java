package com.hexagonalarch.core.ports.usecases.Order;

import com.hexagonalarch.core.domain.Order;

public interface CreateOrderUseCasePort {

    Order createOrder(Order order);

}
