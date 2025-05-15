package com.hexagonalarch.core.ports.gateways;
import com.hexagonalarch.core.domain.OrderPayment;

import java.util.List;
import java.util.Optional;

public interface OrderPaymentGatewayPort {
    OrderPayment save(OrderPayment orderPayment);
    OrderPayment findByMercadoPagoPaymentId(Long id);
    List<OrderPayment> findByOrderId(Long orderId);
}
