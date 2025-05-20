package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.shared.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {

    @Mock
    private OrderGatewayPort orderGatewayPort;

    @Mock
    private ProductGatewayPort productGatewayPort;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    @Test
    void shouldCreateOrder() {
        long productId = 1L;
        long customerId = 100L;

        Product inputProduct = new Product();
        inputProduct.setId(productId);

        Product foundProduct = new Product();
        foundProduct.setId(productId);

        Order inputOrder = new Order();
        inputOrder.setCustomerId(customerId);
        inputOrder.setProducts(List.of(inputProduct));

        Order savedOrder = new Order();
        savedOrder.setId(10L);
        savedOrder.setCustomerId(customerId);
        savedOrder.setStatus(OrderStatus.INICIADO);
        savedOrder.setProducts(List.of(foundProduct));

        given(productGatewayPort.findById(productId)).willReturn(Optional.of(foundProduct));
        given(orderGatewayPort.save(any(Order.class))).willReturn(savedOrder);

        Order result = createOrderUseCase.createOrder(inputOrder);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getCustomerId()).isEqualTo(customerId);
        assertThat(result.getStatus()).isEqualTo(OrderStatus.INICIADO);
        assertThat(result.getProducts()).hasSize(1);
        assertThat(result.getProducts().get(0).getId()).isEqualTo(productId);

        verify(productGatewayPort).findById(productId);
        verify(orderGatewayPort).save(any(Order.class));
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        long productId = 999L;
        long customerId = 100L;

        Product inputProduct = new Product();
        inputProduct.setId(productId);

        Order inputOrder = new Order();
        inputOrder.setCustomerId(customerId);
        inputOrder.setProducts(List.of(inputProduct));

        given(productGatewayPort.findById(productId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> createOrderUseCase.createOrder(inputOrder))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Product not found for ID: " + productId);

        verify(productGatewayPort).findById(productId);
        verify(orderGatewayPort, never()).save(any(Order.class));
    }
}
