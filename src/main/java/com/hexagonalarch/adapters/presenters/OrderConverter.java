package com.hexagonalarch.adapters.presenters;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.domain.ProductCategory;
import com.hexagonalarch.frameworks.jpa.entity.CategoryEntity;
import com.hexagonalarch.frameworks.jpa.entity.OrderEntity;
import com.hexagonalarch.frameworks.jpa.entity.ProductEntity;
import com.hexagonalarch.frameworks.rest.dto.request.CreateOrderRequest;
import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {
    public Order toDomain(CreateOrderRequest request) {
        List<Product> products = request.getProductIds().stream()
                .map(id -> new Product(id))
                .collect(Collectors.toList());
        return new Order(null, request.getCustomerId(), null, products);
    }

    public Order entityToDomain(OrderEntity entity) {
        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getStatus().getStatus(),
                entity.getProducts().stream()
                        .map(p -> new Product(p.getProductId()))
                        .toList()
        );
    }
}
