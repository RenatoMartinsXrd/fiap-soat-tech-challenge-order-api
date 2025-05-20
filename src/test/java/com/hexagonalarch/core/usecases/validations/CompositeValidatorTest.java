package com.hexagonalarch.core.usecases.validations;

import com.hexagonalarch.core.domain.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CompositeValidatorTest {

    @Test
    void shouldReturnValidWhenAllValidatorsPass() {
        Validator<Customer> validator1 = mock(Validator.class);
        Validator<Customer> validator2 = mock(Validator.class);

        when(validator1.validate(any())).thenReturn(new ValidationResult(true, null));
        when(validator2.validate(any())).thenReturn(new ValidationResult(true, null));

        CompositeValidator<Customer> compositeValidator = new CompositeValidator<>(List.of(validator1, validator2));
        ValidationResult result = compositeValidator.validate(new Customer());

        assertThat(result.isValid()).isTrue();
        assertThat(result.getMessage()).isNull();
    }

    @Test
    void shouldReturnFirstInvalidResultAndSkipRemainingValidators() {
        Validator<Customer> validator1 = mock(Validator.class);
        Validator<Customer> validator2 = mock(Validator.class);
        Validator<Customer> validator3 = mock(Validator.class);

        when(validator1.validate(any())).thenReturn(new ValidationResult(true, null));
        when(validator2.validate(any())).thenReturn(new ValidationResult(false, "Invalid email"));
        when(validator3.validate(any())).thenReturn(new ValidationResult(false, "Should not reach here"));

        CompositeValidator<Customer> compositeValidator = new CompositeValidator<>(List.of(validator1, validator2, validator3));
        ValidationResult result = compositeValidator.validate(new Customer());

        assertThat(result.isValid()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Invalid email");

        verify(validator1).validate(any());
        verify(validator2).validate(any());
        verify(validator3, never()).validate(any());
    }
}
