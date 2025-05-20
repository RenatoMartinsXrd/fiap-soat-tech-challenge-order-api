package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckoutOrderUseCaseTest {

    @Mock
    private OrderGatewayPort orderGatewayPort;

    @InjectMocks
    private CheckoutOrderUseCase checkoutOrderUseCase;

    @Test
    void shouldFinalizeOrder() {
        long orderId = 1L;

        Order order = new Order();
        order.setId(orderId);
        order.setStatus(OrderStatus.INICIADO);

        Order finalizedOrder = new Order();
        finalizedOrder.setId(orderId);
        finalizedOrder.setStatus(OrderStatus.FINALIZADO);

        given(orderGatewayPort.findById(orderId)).willReturn(Optional.of(order));
        given(orderGatewayPort.save(order)).willReturn(finalizedOrder);

        Order result = checkoutOrderUseCase.processCheckout(orderId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(orderId);
        assertThat(result.getStatus()).isEqualTo(OrderStatus.FINALIZADO);

        verify(orderGatewayPort).findById(orderId);
        verify(orderGatewayPort).save(order);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        long orderId = 999L;

        given(orderGatewayPort.findById(orderId)).willReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(
                java.util.NoSuchElementException.class,
                () -> checkoutOrderUseCase.processCheckout(orderId)
        );

        verify(orderGatewayPort).findById(orderId);
        verify(orderGatewayPort, never()).save(any());
    }
}
