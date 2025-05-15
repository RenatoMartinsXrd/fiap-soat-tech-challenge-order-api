package com.hexagonalarch.core.ports.gateways;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.OrderPayment;

public interface PaymentGatewayPort {
    OrderPayment generatePayment(Order order);
    OrderPayment getPaymentByOrderId(OrderPayment order);
    OrderPayment updatePaymentStatus(OrderPayment orderPayment);
}
