package com.hexagonalarch.core.usecases.validations.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import com.hexagonalarch.core.usecases.validations.Validator;

public class CustomerNameValidation implements Validator<Customer> {
    @Override
    public ValidationResult validate(Customer customer) {
        if (customer.getName() == null || customer.getName().isEmpty()) {
            return new ValidationResult(false, "Nome não enviado");
        }
        return new ValidationResult(true, null);
    }
}
