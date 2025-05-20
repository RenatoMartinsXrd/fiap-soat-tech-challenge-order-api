package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.shared.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateOrderStatusUseCaseTest {

    @Mock
    private OrderGatewayPort orderGatewayPort;

    @InjectMocks
    private UpdateOrderStatusUseCase updateOrderStatusUseCase;

    @Test
    void shouldUpdateOrderStatus() {
        long orderId = 1L;

        Order order = new Order();
        order.setId(orderId);
        order.setStatus(OrderStatus.INICIADO);

        given(orderGatewayPort.findById(orderId)).willReturn(Optional.of(order));

        updateOrderStatusUseCase.updateOrderStatus(orderId, OrderStatus.FINALIZADO);

        verify(orderGatewayPort).findById(orderId);
        verify(orderGatewayPort).save(order);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        long orderId = 99L;

        given(orderGatewayPort.findById(orderId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> updateOrderStatusUseCase.updateOrderStatus(orderId, OrderStatus.FINALIZADO))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Order not found");

        verify(orderGatewayPort).findById(orderId);
        verify(orderGatewayPort, never()).save(any());
    }
}
