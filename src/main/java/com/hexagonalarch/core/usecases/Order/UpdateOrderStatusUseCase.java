package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.usecases.Order.UpdateOrderStatusUseCasePort;
import com.hexagonalarch.shared.exception.NotFoundException;

public class UpdateOrderStatusUseCase implements UpdateOrderStatusUseCasePort {
    private final OrderGatewayPort orderGatewayPort;

    public UpdateOrderStatusUseCase(OrderGatewayPort orderGatewayPort) {
        this.orderGatewayPort = orderGatewayPort;
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderGatewayPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(status);
        orderGatewayPort.save(order);
    }
}
