package com.hexagonalarch.core.ports.usecases.Order;

import com.hexagonalarch.core.domain.enumeration.OrderStatus;

public interface UpdateOrderStatusUseCasePort {

    void updateOrderStatus(Long id, OrderStatus status);

}
