package com.hexagonalarch.frameworks.rest.dto.response;

import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResponse {

    private Long id;

    private Long customerId;

    private OrderStatus status;

    private List<CreateProductResponse> products;

}