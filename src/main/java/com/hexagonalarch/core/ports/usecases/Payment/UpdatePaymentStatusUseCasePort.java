package com.hexagonalarch.core.ports.usecases.Payment;

import com.hexagonalarch.core.domain.OrderPayment;
import com.hexagonalarch.core.domain.enumeration.PaymentStatus;

public interface UpdatePaymentStatusUseCasePort {
    OrderPayment updateStatusPayment(Long orderId, PaymentStatus paymentStatus);
}
