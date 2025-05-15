package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.usecases.Order.CheckoutUseCasePort;

import java.util.Optional;

public class CheckoutOrderUseCase implements CheckoutUseCasePort {
    private final OrderGatewayPort orderGatewayPort;

    public CheckoutOrderUseCase(OrderGatewayPort orderGatewayPort) {
        this.orderGatewayPort = orderGatewayPort;
    }

    @Override
    public Order processCheckout(Long id) {
        Optional<Order> byId = orderGatewayPort.findById(id);
        Order order = byId.get();
        order.setStatus(OrderStatus.FINALIZADO);
        return orderGatewayPort.save(order);
    }
}
