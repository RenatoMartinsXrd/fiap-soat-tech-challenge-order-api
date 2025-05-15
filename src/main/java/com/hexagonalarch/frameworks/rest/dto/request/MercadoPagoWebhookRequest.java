package com.hexagonalarch.frameworks.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoWebhookRequest {
    private String type;
    private boolean liveMode;
    private String action;
    private DataObject data;
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataObject {
        private String id;
    }
}
