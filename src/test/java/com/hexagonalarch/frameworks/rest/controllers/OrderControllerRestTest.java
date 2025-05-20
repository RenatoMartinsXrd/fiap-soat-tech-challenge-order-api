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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerRestTest {

    @Mock
    private OrderController orderController;

    @Mock
    private GenericConverter genericConverter;

    @Mock
    private OrderConverter orderConverter;

    @InjectMocks
    private OrderControllerRest orderControllerRest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateOrderSuccessfully() {
        CreateOrderRequest request = new CreateOrderRequest();
        Order domainOrder = new Order();
        Order savedOrder = new Order();
        CreateOrderResponse response = new CreateOrderResponse();

        when(orderConverter.toDomain(request)).thenReturn(domainOrder);
        when(orderController.createOrder(domainOrder)).thenReturn(savedOrder);
        when(genericConverter.toDto(savedOrder, CreateOrderResponse.class)).thenReturn(response);

        CreateOrderResponse result = orderControllerRest.createOrder(request);

        assertNotNull(result);
        assertEquals(response, result);
    }

    @Test
    void shouldReturnOrderById() {
        Long id = 1L;
        Order order = new Order();
        GetOrderResponse response = new GetOrderResponse();

        when(orderController.getOrderById(id)).thenReturn(order);
        when(genericConverter.toDto(order, GetOrderResponse.class)).thenReturn(response);

        GetOrderResponse result = orderControllerRest.getOrderById(id);

        assertNotNull(result);
        assertEquals(response, result);
    }

    @Test
    void shouldReturnAllOrders() {
        Order order = new Order();
        GetOrderResponse response = new GetOrderResponse();

        when(orderController.getAllOrders(null)).thenReturn(List.of(order));
        when(genericConverter.toDto(order, GetOrderResponse.class)).thenReturn(response);

        List<GetOrderResponse> result = orderControllerRest.getAllOrders(null);

        assertEquals(1, result.size());
        assertEquals(response, result.get(0));
    }

    @Test
    void shouldUpdateOrderStatus() {
        Long id = 1L;
        UpdateOrderStatusRequest request = new UpdateOrderStatusRequest();
        request.setStatus(OrderStatus.FINALIZADO);

        orderControllerRest.updateOrderStatus(id, request);

        verify(orderController).updateOrderStatus(id, OrderStatus.FINALIZADO);
    }

    @Test
    void shouldCheckoutOrder() {
        Long id = 1L;
        Order order = new Order();
        GetOrderResponse response = new GetOrderResponse();

        when(orderController.checkout(id)).thenReturn(order);
        when(genericConverter.toDto(order, GetOrderResponse.class)).thenReturn(response);

        GetOrderResponse result = orderControllerRest.checkout(id);

        assertNotNull(result);
        assertEquals(response, result);
    }
}
