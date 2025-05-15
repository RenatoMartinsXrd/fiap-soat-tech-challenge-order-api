package com.hexagonalarch.core.ports.usecases.Payment;

import com.hexagonalarch.core.domain.dto.PaymentNotificationDto;

public interface PaymentWebhookUseCasePort {
    void processPaymentWebhook(PaymentNotificationDto paymentNotificationDto);
}
