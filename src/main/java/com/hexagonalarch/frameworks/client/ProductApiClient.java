package com.hexagonalarch.frameworks.client;

import com.hexagonalarch.adapters.presenters.GenericConverter;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.frameworks.rest.dto.response.GetProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class ProductApiClient implements ProductGatewayPort {
    private final RestTemplate restTemplate;
    private final String productApiUrl;
    private final GenericConverter genericConverter;

    public ProductApiClient(
            RestTemplate restTemplate,
            @Value("${external.product-api.url}") String productApiUrl,
            GenericConverter genericConverter
    ) {
        this.restTemplate = restTemplate;
        this.productApiUrl = productApiUrl;
        this.genericConverter = genericConverter;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            GetProductResponse response = restTemplate.getForObject(
                    productApiUrl + "/products/" + id,
                    GetProductResponse.class
            );
            return Optional.ofNullable(response)
                    .map(dto -> genericConverter.toDomain(dto, Product.class));
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }
}
