package com.hexagonalarch.frameworks.rest.controllers;

import com.hexagonalarch.adapters.controllers.OrderController;
import com.hexagonalarch.adapters.presenters.GenericConverter;
import com.hexagonalarch.adapters.presenters.OrderConverter;
import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.frameworks.rest.dto.request.CreateOrderRequest;
import com.hexagonalarch.frameworks.rest.dto.request.UpdateOrderStatusRequest;
import com.hexagonalarch.frameworks.rest.dto.response.CreateOrderResponse;
import com.hexagonalarch.frameworks.rest.dto.response.GetOrderResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Operations related to Orders")
public class OrderControllerRest {

    private final OrderController orderController;
    private final GenericConverter genericConverter;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        Order order = orderConverter.toDomain(createOrderRequest);
        Order newOrder = orderController.createOrder(order);
        return genericConverter.toDto(newOrder, CreateOrderResponse.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetOrderResponse getOrderById(@PathVariable Long id) {
        Order order = orderController.getOrderById(id);
        return genericConverter.toDto(order, GetOrderResponse.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetOrderResponse> getAllOrders(@RequestParam(required = false) OrderStatus statusFilter) {
        List<Order> orders = orderController.getAllOrders(statusFilter);
        return orders.stream()
                .map(order -> genericConverter.toDto(order, GetOrderResponse.class))
                .toList();
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrderStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) {
        orderController.updateOrderStatus(id, updateOrderStatusRequest.getStatus());
    }

    @PatchMapping("/{id}/checkout")
    @ResponseStatus(HttpStatus.OK)
    public GetOrderResponse checkout(@PathVariable Long id) {
        Order checkout = orderController.checkout(id);
        return genericConverter.toDto(checkout, GetOrderResponse.class);
    }
}
