package com.hexagonalarch.core.ports.usecases.Order;

import com.hexagonalarch.core.domain.Order;

public interface CheckoutUseCasePort {
    Order processCheckout(Long id);
}
