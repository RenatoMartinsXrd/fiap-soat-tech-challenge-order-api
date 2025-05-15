package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.core.ports.usecases.Order.GetAllOrdersUseCasePort;
import com.hexagonalarch.shared.exception.NotFoundException;

import java.util.List;

public class GetAllOrdersUseCase implements GetAllOrdersUseCasePort {
    private OrderGatewayPort orderGatewayPort;
    private final ProductGatewayPort productGatewayPort;

    public GetAllOrdersUseCase(OrderGatewayPort orderGatewayPort, ProductGatewayPort productGatewayPort) {
        this.orderGatewayPort = orderGatewayPort;
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public List<Order> getAllOrders(OrderStatus statusFilter) {
        List<Order> orders = orderGatewayPort.findAll(statusFilter);

        for (Order order : orders) {
            List<Product> fullProducts = order.getProducts().stream()
                    .map(product -> productGatewayPort.findById(product.getId())
                            .orElseThrow(() -> new NotFoundException("Product not found: " + product.getId())))
                    .toList();
            order.setProducts(fullProducts);
        }

        return orders;
    }
}
