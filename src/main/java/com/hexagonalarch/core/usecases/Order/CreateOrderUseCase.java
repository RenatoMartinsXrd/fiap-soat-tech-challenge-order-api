package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.core.ports.usecases.Order.CreateOrderUseCasePort;
import com.hexagonalarch.core.usecases.validations.factory.OrderValidationFactory;
import com.hexagonalarch.shared.exception.NotFoundException;

import java.util.List;

public class CreateOrderUseCase implements CreateOrderUseCasePort {
    private final OrderGatewayPort orderGatewayPort;
    private final ProductGatewayPort productGatewayPort;

    public CreateOrderUseCase(OrderGatewayPort orderGatewayPort, ProductGatewayPort productGatewayPort) {
        this.orderGatewayPort = orderGatewayPort;
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public Order createOrder(Order order) {
        List<Product> products = order.getProducts().stream()
                .map(product -> productGatewayPort.findById(product.getId())
                        .orElseThrow(() -> new NotFoundException("Product not found for ID: " + product.getId())))
                .toList();

        order.setProducts(products);
        order.setStatus(OrderStatus.INICIADO);
        order.setCustomerId(order.getCustomerId());

        OrderValidationFactory.getValidatorsForCreateOrder().validate(order);

        Order savedOrder = orderGatewayPort.save(order);
        savedOrder.setProducts(products);

        return savedOrder;
    }
}
