package com.hexagonalarch.core.ports.usecases.Payment;

import com.hexagonalarch.core.domain.OrderPayment;

public interface GetPaymentByOrderUseCasePort {
    OrderPayment getPaymentByOrderId(Long orderId);
}
