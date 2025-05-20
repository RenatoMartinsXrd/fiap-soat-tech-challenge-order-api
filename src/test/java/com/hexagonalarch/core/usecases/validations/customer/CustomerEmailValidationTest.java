package com.hexagonalarch.core.usecases.validations.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerEmailValidationTest {

    private final CustomerEmailValidation validation = new CustomerEmailValidation();

    @Test
    void shouldReturnValidWhenEmailIsPresent() {
        Customer customer = new Customer();
        customer.setEmail("example@email.com");

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isTrue();
        assertThat(result.getMessage()).isNull();
    }

    @Test
    void shouldReturnInvalidWhenEmailIsNull() {
        Customer customer = new Customer();
        customer.setEmail(null);

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Email não enviado");
    }

    @Test
    void shouldReturnInvalidWhenEmailIsEmpty() {
        Customer customer = new Customer();
        customer.setEmail("");

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Email não enviado");
    }
}
