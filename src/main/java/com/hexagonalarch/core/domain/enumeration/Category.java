package com.hexagonalarch.core.domain.enumeration;

import java.util.Arrays;
import java.util.List;

public enum Category {
    LANCHE,
    ACOMPANHAMENTO,
    BEBIDA,
    SOBREMESA;
    public static List<String> getAllCategories() {
        return Arrays.stream(Category.values())
                .map(Enum::name)
                .toList();
    }
}

