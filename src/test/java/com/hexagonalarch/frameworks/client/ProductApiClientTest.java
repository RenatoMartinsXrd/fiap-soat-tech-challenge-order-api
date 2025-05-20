package com.hexagonalarch.frameworks.client;

import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.frameworks.rest.dto.response.GetProductResponse;
import com.hexagonalarch.adapters.presenters.GenericConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProductApiClientTest {

    private RestTemplate restTemplate;
    private GenericConverter genericConverter;
    private ProductApiClient productApiClient;

    private final String productApiUrl = "http://localhost:8080";

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        genericConverter = mock(GenericConverter.class);
        productApiClient = new ProductApiClient(restTemplate, productApiUrl, genericConverter);
    }

    @Test
    void shouldReturnProductWhenFound() {
        Long id = 1L;
        GetProductResponse response = new GetProductResponse();
        Product product = new Product();

        when(restTemplate.getForObject(productApiUrl + "/products/" + id, GetProductResponse.class))
                .thenReturn(response);
        when(genericConverter.toDomain(response, Product.class)).thenReturn(product);

        Optional<Product> result = productApiClient.findById(id);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(product);
    }

    @Test
    void shouldReturnEmptyWhenProductNotFound() {
        Long id = 999L;

        when(restTemplate.getForObject(productApiUrl + "/products/" + id, GetProductResponse.class))
                .thenThrow(HttpClientErrorException.NotFound.class);

        Optional<Product> result = productApiClient.findById(id);

        assertThat(result).isEmpty();
    }
}
