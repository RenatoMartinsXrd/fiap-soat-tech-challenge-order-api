package com.hexagonalarch.core.domain.dto;

public class PaymentNotificationDto {

    private String type;
    private String paymentId;
    private String action;
    private boolean liveMode;

    public PaymentNotificationDto(String type, String paymentId, String action, boolean liveMode) {
        this.type = type;
        this.paymentId = paymentId;
        this.action = action;
        this.liveMode = liveMode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isLiveMode() {
        return liveMode;
    }

    public void setLiveMode(boolean liveMode) {
        this.liveMode = liveMode;
    }

    @Override
    public String toString() {
        return "PaymentNotificationDto{" +
                "type='" + type + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", action='" + action + '\'' +
                ", liveMode=" + liveMode +
                '}';
    }
}

