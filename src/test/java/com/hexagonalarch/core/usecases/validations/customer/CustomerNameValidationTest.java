package com.hexagonalarch.core.usecases.validations.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerNameValidationTest {

    private final CustomerNameValidation validation = new CustomerNameValidation();

    @Test
    void shouldReturnValidWhenNameIsPresent() {
        Customer customer = new Customer();
        customer.setName("Daniel");

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isTrue();
        assertThat(result.getMessage()).isNull();
    }

    @Test
    void shouldReturnInvalidWhenNameIsNull() {
        Customer customer = new Customer();
        customer.setName(null);

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Nome não enviado");
    }

    @Test
    void shouldReturnInvalidWhenNameIsEmpty() {
        Customer customer = new Customer();
        customer.setName("");

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Nome não enviado");
    }
}
