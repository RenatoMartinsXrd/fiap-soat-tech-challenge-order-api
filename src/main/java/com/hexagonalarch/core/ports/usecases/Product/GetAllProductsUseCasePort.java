package com.hexagonalarch.core.ports.usecases.Product;

import com.hexagonalarch.core.domain.Product;

import java.util.List;

public interface GetAllProductsUseCasePort {

    List<Product> getAllProducts();

}
