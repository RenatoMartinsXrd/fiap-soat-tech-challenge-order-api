package com.hexagonalarch.core.domain;

import com.hexagonalarch.core.domain.enumeration.OrderStatus;

import java.util.List;

public class Order {

    private Long id;

    private Long customerId;

    private OrderStatus status;
    
    private List<Product> products;

    public Order() {
    }

    public Order(Long id, Long customerId, OrderStatus status, List<Product> products) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getTotalPrice() {
        if (products == null) {
            return 0.0;
        }
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}