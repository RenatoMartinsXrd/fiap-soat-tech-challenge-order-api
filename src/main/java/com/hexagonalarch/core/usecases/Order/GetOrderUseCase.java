package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.core.ports.usecases.Order.GetOrderUseCasePort;
import com.hexagonalarch.shared.exception.NotFoundException;

import java.util.List;

public class GetOrderUseCase implements GetOrderUseCasePort {
    private final OrderGatewayPort orderGatewayPort;
    private final ProductGatewayPort productGatewayPort;

    public GetOrderUseCase(OrderGatewayPort orderGatewayPort, ProductGatewayPort productGatewayPort) {
        this.orderGatewayPort = orderGatewayPort;
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = orderGatewayPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        List<Product> fullProducts = order.getProducts().stream()
                .map(product -> productGatewayPort.findById(product.getId())
                        .orElseThrow(() -> new NotFoundException("Product not found: " + product.getId())))
                .toList();

        order.setProducts(fullProducts);
        return order;
    }
}
