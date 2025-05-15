package com.hexagonalarch.core.usecases.validations.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import com.hexagonalarch.core.usecases.validations.Validator;

public class CustomerEmailValidation implements Validator<Customer> {
    @Override
    public ValidationResult validate(Customer customer) {
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            return new ValidationResult(false, "Email não enviado");
        }
        return new ValidationResult(true, null);
    }
}
