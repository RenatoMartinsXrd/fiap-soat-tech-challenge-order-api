package com.hexagonalarch.adapters.controllers;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.usecases.Order.CheckoutUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.CreateOrderUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.GetAllOrdersUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.GetOrderUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.UpdateOrderStatusUseCasePort;

import java.util.List;

public class OrderController {

    private final CreateOrderUseCasePort createOrderUseCase;
    private final GetOrderUseCasePort getOrderUseCase;
    private final GetAllOrdersUseCasePort getAllOrdersUseCase;
    private final UpdateOrderStatusUseCasePort updateOrderStatusUseCase;
    private final CheckoutUseCasePort checkoutUseCase;

    public OrderController(CreateOrderUseCasePort createOrderUseCase,
                           GetOrderUseCasePort getOrderUseCase,
                           GetAllOrdersUseCasePort getAllOrdersUseCase,
                           UpdateOrderStatusUseCasePort updateOrderStatusUseCase, CheckoutUseCasePort checkoutUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.getAllOrdersUseCase = getAllOrdersUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
        this.checkoutUseCase = checkoutUseCase;
    }


    public Order createOrder(Order order) {
        return createOrderUseCase.createOrder(order);
    }

    public Order getOrderById(Long id) {
        return getOrderUseCase.getOrderById(id);
    }

    public List<Order> getAllOrders(OrderStatus statusFilter) {
        return getAllOrdersUseCase.getAllOrders(statusFilter);
    }

    public void updateOrderStatus(Long id, OrderStatus status) {
        updateOrderStatusUseCase.updateOrderStatus(id, status);
    }

    public Order checkout(Long id){
        return checkoutUseCase.processCheckout(id);
    }
}
