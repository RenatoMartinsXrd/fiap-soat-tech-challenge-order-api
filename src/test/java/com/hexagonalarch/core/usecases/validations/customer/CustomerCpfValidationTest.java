package com.hexagonalarch.core.usecases.validations.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerCpfValidationTest {

    private final CustomerCpfValidation validation = new CustomerCpfValidation();

    @Test
    void shouldReturnValidWhenCpfIsPresent() {
        Customer customer = new Customer();
        customer.setCpf("123.456.789-00");

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isTrue();
        assertThat(result.getMessage()).isNull();
    }

    @Test
    void shouldReturnInvalidWhenCpfIsNull() {
        Customer customer = new Customer();
        customer.setCpf(null);

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).isEqualTo("CPF não enviado");
    }

    @Test
    void shouldReturnInvalidWhenCpfIsEmpty() {
        Customer customer = new Customer();
        customer.setCpf("");

        ValidationResult result = validation.validate(customer);

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).isEqualTo("CPF não enviado");
    }
}
