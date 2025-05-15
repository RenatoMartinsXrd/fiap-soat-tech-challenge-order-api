package com.hexagonalarch.shared.config;

import com.hexagonalarch.adapters.controllers.OrderController;
import com.hexagonalarch.core.ports.gateways.*;
import com.hexagonalarch.core.ports.usecases.Order.CheckoutUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.CreateOrderUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.GetAllOrdersUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.GetOrderUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.UpdateOrderStatusUseCasePort;
import com.hexagonalarch.core.usecases.Order.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OrderController orderController(CreateOrderUseCasePort createOrderUseCase,
                                           GetOrderUseCasePort getOrderUseCase,
                                           GetAllOrdersUseCasePort getAllOrdersUseCase,
                                           UpdateOrderStatusUseCasePort updateOrderStatusUseCase,
                                           CheckoutUseCasePort checkoutUseCase) {
        return new OrderController(createOrderUseCase, getOrderUseCase, getAllOrdersUseCase, updateOrderStatusUseCase, checkoutUseCase);
    }
//    @Bean
//    public CreateOrderUseCasePort createOrderUseCasePort(OrderGatewayPort orderGatewayPort, CustomerGatewayPort customerGatewayPort, ProductGatewayPort productGatewayPort) {
//        return new CreateOrderUseCase(orderGatewayPort, customerGatewayPort, productGatewayPort);
//    }

    @Bean
    public CreateOrderUseCasePort createOrderUseCasePort(OrderGatewayPort orderGatewayPort, ProductGatewayPort productGatewayPort) {
        return new CreateOrderUseCase(orderGatewayPort, productGatewayPort);
    }

    @Bean
    public GetOrderUseCasePort GetOrderUseCasePort(OrderGatewayPort orderGatewayPort, ProductGatewayPort productGatewayPort) {
        return new GetOrderUseCase(orderGatewayPort, productGatewayPort);
    }

    @Bean
    public GetAllOrdersUseCasePort GetAllOrdersUseCasePort(OrderGatewayPort orderGatewayPort, ProductGatewayPort productGatewayPort) {
        return new GetAllOrdersUseCase(orderGatewayPort, productGatewayPort);
    }

    @Bean
    public CheckoutUseCasePort checkoutUseCasePort(OrderGatewayPort orderRepositoryPort) {
        return new CheckoutOrderUseCase(orderRepositoryPort);
    }

    @Bean
    public UpdateOrderStatusUseCasePort updateOrderStatusUseCasePort(OrderGatewayPort orderRepositoryPort) {
        return new UpdateOrderStatusUseCase(orderRepositoryPort);
    }
}
