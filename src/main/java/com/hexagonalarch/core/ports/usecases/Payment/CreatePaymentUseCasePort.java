package com.hexagonalarch.core.ports.usecases.Payment;

import com.hexagonalarch.core.domain.OrderPayment;

public interface CreatePaymentUseCasePort {
    OrderPayment createPayment(Long orderId);
}
