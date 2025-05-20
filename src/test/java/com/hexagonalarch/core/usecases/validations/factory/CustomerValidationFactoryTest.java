package com.hexagonalarch.core.usecases.validations.factory;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.validations.CompositeValidator;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import com.hexagonalarch.core.usecases.validations.Validator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerValidationFactoryTest {

    @Test
    void shouldReturnCompositeValidatorWithCustomerIsNotNullValidation() {
        Validator<Customer> validator = CustomerValidationFactory.getCustomerNotNullValidation();
        assertThat(validator).isInstanceOf(CompositeValidator.class);
        ValidationResult result = validator.validate(new Customer());
        assertThat(result).isNotNull();
    }

    @Test
    void shouldReturnCompositeValidatorWithCustomerNameValidation() {
        Validator<Customer> validator = CustomerValidationFactory.getNameValidation();
        assertThat(validator).isInstanceOf(CompositeValidator.class);
        ValidationResult result = validator.validate(new Customer());
        assertThat(result).isNotNull();
    }

    @Test
    void shouldReturnCompositeValidatorWithCustomerEmailValidation() {
        Validator<Customer> validator = CustomerValidationFactory.getEmailValidation();
        assertThat(validator).isInstanceOf(CompositeValidator.class);
        ValidationResult result = validator.validate(new Customer());
        assertThat(result).isNotNull();
    }

    @Test
    void shouldReturnCompositeValidatorWithCustomerCpfValidation() {
        Validator<Customer> validator = CustomerValidationFactory.getCustomerCpfValidation();
        assertThat(validator).isInstanceOf(CompositeValidator.class);
        ValidationResult result = validator.validate(new Customer());
        assertThat(result).isNotNull();
    }
}
