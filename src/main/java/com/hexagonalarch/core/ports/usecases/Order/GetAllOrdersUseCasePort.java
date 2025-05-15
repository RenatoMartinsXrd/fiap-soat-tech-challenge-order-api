package com.hexagonalarch.core.ports.usecases.Order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;

import java.util.List;

public interface GetAllOrdersUseCasePort {

    List<Order> getAllOrders(OrderStatus statusFilter);

}
