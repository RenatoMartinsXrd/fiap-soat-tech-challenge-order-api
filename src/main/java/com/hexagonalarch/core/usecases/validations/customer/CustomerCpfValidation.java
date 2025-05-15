package com.hexagonalarch.core.usecases.validations.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import com.hexagonalarch.core.usecases.validations.Validator;

public class CustomerCpfValidation implements Validator<Customer> {
    @Override
    public ValidationResult validate(Customer customer) {
        if (customer.getCpf() == null || customer.getCpf().isEmpty()) {
            return new ValidationResult(false, "CPF não enviado");
        }
        return new ValidationResult(true, null);
    }
}