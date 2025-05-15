package com.hexagonalarch.core.ports.usecases.Order;

import com.hexagonalarch.core.domain.Order;

public interface GetOrderUseCasePort {

    Order getOrderById(Long id);

}
