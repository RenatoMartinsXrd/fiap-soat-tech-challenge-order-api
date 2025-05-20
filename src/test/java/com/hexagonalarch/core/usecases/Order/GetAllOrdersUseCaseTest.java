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
class GetAllOrdersUseCaseTest {

    @Mock
    private OrderGatewayPort orderGatewayPort;

    @Mock
    private ProductGatewayPort productGatewayPort;

    @InjectMocks
    private GetAllOrdersUseCase getAllOrdersUseCase;

    @Test
    void shouldReturnOrdersWithFullProducts() {
        long productId = 1L;
        long orderId = 10L;

        Product partialProduct = new Product();
        partialProduct.setId(productId);

        Product fullProduct = new Product();
        fullProduct.setId(productId);
        fullProduct.setName("Test Product");

        Order order = new Order();
        order.setId(orderId);
        order.setStatus(OrderStatus.INICIADO);
        order.setProducts(List.of(partialProduct));

        given(orderGatewayPort.findAll(OrderStatus.INICIADO)).willReturn(List.of(order));
        given(productGatewayPort.findById(productId)).willReturn(Optional.of(fullProduct));

        List<Order> result = getAllOrdersUseCase.getAllOrders(OrderStatus.INICIADO);

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getProducts()).hasSize(1);
        assertThat(result.getFirst().getProducts().getFirst().getId()).isEqualTo(productId);
        assertThat(result.getFirst().getProducts().getFirst().getName()).isEqualTo("Test Product");

        verify(orderGatewayPort).findAll(OrderStatus.INICIADO);
        verify(productGatewayPort).findById(productId);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        long productId = 99L;

        Product partialProduct = new Product();
        partialProduct.setId(productId);

        Order order = new Order();
        order.setId(1L);
        order.setProducts(List.of(partialProduct));
        order.setStatus(OrderStatus.INICIADO);

        given(orderGatewayPort.findAll(OrderStatus.INICIADO)).willReturn(List.of(order));
        given(productGatewayPort.findById(productId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> getAllOrdersUseCase.getAllOrders(OrderStatus.INICIADO))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Product not found: " + productId);

        verify(productGatewayPort).findById(productId);
        verify(orderGatewayPort).findAll(OrderStatus.INICIADO);
    }
}
