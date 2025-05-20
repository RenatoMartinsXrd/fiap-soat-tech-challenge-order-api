package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.Product;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GetOrderUseCaseTest {

    @Mock
    private OrderGatewayPort orderGatewayPort;

    @Mock
    private ProductGatewayPort productGatewayPort;

    @InjectMocks
    private GetOrderUseCase getOrderUseCase;

    @Test
    void shouldReturnOrderWithFullProducts() {
        long orderId = 1L;
        long productId = 10L;

        Product partialProduct = new Product();
        partialProduct.setId(productId);

        Product fullProduct = new Product();
        fullProduct.setId(productId);
        fullProduct.setName("Product Name");

        Order order = new Order();
        order.setId(orderId);
        order.setProducts(List.of(partialProduct));

        given(orderGatewayPort.findById(orderId)).willReturn(Optional.of(order));
        given(productGatewayPort.findById(productId)).willReturn(Optional.of(fullProduct));

        Order result = getOrderUseCase.getOrderById(orderId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(orderId);
        assertThat(result.getProducts()).hasSize(1);
        assertThat(result.getProducts().getFirst().getId()).isEqualTo(productId);
        assertThat(result.getProducts().getFirst().getName()).isEqualTo("Product Name");

        verify(orderGatewayPort).findById(orderId);
        verify(productGatewayPort).findById(productId);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        long orderId = 1L;

        given(orderGatewayPort.findById(orderId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> getOrderUseCase.getOrderById(orderId))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Order not found");

        verify(orderGatewayPort).findById(orderId);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        long orderId = 1L;
        long productId = 10L;

        Product partialProduct = new Product();
        partialProduct.setId(productId);

        Order order = new Order();
        order.setId(orderId);
        order.setProducts(List.of(partialProduct));

        given(orderGatewayPort.findById(orderId)).willReturn(Optional.of(order));
        given(productGatewayPort.findById(productId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> getOrderUseCase.getOrderById(orderId))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Product not found: " + productId);

        verify(orderGatewayPort).findById(orderId);
        verify(productGatewayPort).findById(productId);
    }
}
