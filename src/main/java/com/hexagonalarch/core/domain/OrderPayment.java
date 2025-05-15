package com.hexagonalarch.core.domain;
import com.hexagonalarch.core.domain.enumeration.PaymentStatus;

import java.time.LocalDateTime;

public class OrderPayment {
    private Long id;
    private Long mercadoPagoPaymentId;
    private PaymentStatus status;
    private String qrCode;
    private Long orderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderPayment() {
    }

    public OrderPayment(Long id, Long mercadoPagoPaymentId, PaymentStatus status, String qrCode, Long orderId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.mercadoPagoPaymentId = mercadoPagoPaymentId;
        this.status = status;
        this.qrCode = qrCode;
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public OrderPayment(Long mercadoPagoPaymentId, PaymentStatus status, Long orderId) {
        this.mercadoPagoPaymentId = mercadoPagoPaymentId;
        this.status = status;
        this.orderId = orderId;
    }

    public OrderPayment(Long mercadoPagoPaymentId, PaymentStatus status, Long orderId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.mercadoPagoPaymentId = mercadoPagoPaymentId;
        this.status = status;
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMercadoPagoPaymentId() {
        return mercadoPagoPaymentId;
    }

    public void setMercadoPagoPaymentId(Long mercadoPagoPaymentId) {
        this.mercadoPagoPaymentId = mercadoPagoPaymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "OrderPayment{" +
                "id=" + id +
                ", mercadoPagoPaymentId='" + mercadoPagoPaymentId + '\'' +
                ", status=" + status +
                ", orderId=" + orderId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
