package com.hexagonalarch.frameworks.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private Long customerId;

    @NotNull(message = "productIds must not be null")
    private List<Long> productIds;

}