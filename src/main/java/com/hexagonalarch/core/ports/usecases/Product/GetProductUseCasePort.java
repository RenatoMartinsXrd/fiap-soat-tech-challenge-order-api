package com.hexagonalarch.core.ports.usecases.Product;

import com.hexagonalarch.core.domain.Product;

public interface GetProductUseCasePort {

    Product getProductById(Long id);

}
