package com.hexagonalarch.adapters.presenters;

import com.hexagonalarch.frameworks.rest.dto.request.CreateProductRequest;
import com.hexagonalarch.frameworks.jpa.entity.CategoryEntity;
import com.hexagonalarch.frameworks.jpa.entity.ProductEntity;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.domain.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

//    public Product toDomain(CreateProductRequest request) {
//        return new Product(null, request.getName(), request.getDescription(),
//                request.getPrice(), new ProductCategory(request.getCategory()));
//    }
//
//    public Product entityToDomain(ProductEntity productEntity) {
//        if (productEntity == null) return null;
//        ProductCategory category = new ProductCategory(productEntity.getCategory().getName());
//        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getDescription(),
//                productEntity.getPrice(), category);
//    }
//
//    public ProductEntity domainToEntity(Product product) {
//        if (product == null) return null;
//
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setName(product.getCategory().getValue());
//
//        return ProductEntity.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .description(product.getDescription())
//                .price(product.getPrice())
//                .category(categoryEntity)
//                .build();
//    }
}
