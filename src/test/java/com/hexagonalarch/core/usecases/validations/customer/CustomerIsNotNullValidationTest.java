package com.hexagonalarch.core.usecases.validations.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerIsNotNullValidationTest {

    private final CustomerIsNotNullValidation validation = new CustomerIsNotNullValidation();

    @Test
    void shouldReturnValidWhenAllFieldsArePresent() {
        Customer customer = new Customer();
        customer.setCpf("123");
        customer.setEmail("a@b.com");
        customer.setName("Daniel");

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isTrue();
        assertThat(result.getMessage()).isNull();
    }

    @Test
    void shouldReturnInvalidWhenAllFieldsAreNull() {
        Customer customer = new Customer();
        customer.setCpf(null);
        customer.setEmail(null);
        customer.setName(null);

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Cliente não identificado, dados estão nulos");
    }

    @Test
    void shouldReturnInvalidWhenAllFieldsAreEmpty() {
        Customer customer = new Customer();
        customer.setCpf("");
        customer.setEmail("");
        customer.setName("");

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Cliente não identificado, dados estão nulos");
    }

    @Test
    void shouldReturnInvalidWhenSomeFieldsAreEmptyAndOthersNull() {
        Customer customer = new Customer();
        customer.setCpf("");
        customer.setEmail(null);
        customer.setName("");

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Cliente não identificado, dados estão nulos");
    }
}
