package com.hexagonalarch.frameworks.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderResponse {

    private Long orderId;

    private String status;

    private List<CreateOrderProductResponse> products;

}
