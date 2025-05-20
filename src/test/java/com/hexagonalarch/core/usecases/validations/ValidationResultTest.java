package com.hexagonalarch.core.usecases.validations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidationResultTest {

    @Test
    void shouldCreateValidResultWithNullMessage() {
        ValidationResult result = new ValidationResult(true, null);

        assertThat(result.isValid()).isTrue();
        assertThat(result.getMessage()).isNull();
    }

    @Test
    void shouldCreateInvalidResultWithMessage() {
        ValidationResult result = new ValidationResult(false, "Invalid data");

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Invalid data");
    }
}
