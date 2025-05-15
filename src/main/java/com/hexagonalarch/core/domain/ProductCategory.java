package com.hexagonalarch.core.domain;
import com.hexagonalarch.core.domain.enumeration.Category;

import java.util.Objects;

public class ProductCategory {
    private final Category category;

    public ProductCategory(String value) {
        validate(value);
        this.category = Category.valueOf(value);
    }

    public String getValue() {
        return category.name();
    }

    private void validate(String value) {
        try {
            Category.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoria inválida: selecione uma das opções: " + Category.getAllCategories());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }

    @Override
    public String toString() {
        return category.name();
    }
}