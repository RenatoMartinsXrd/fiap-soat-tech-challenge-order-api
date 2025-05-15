package com.hexagonalarch.core.usecases.validations.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import com.hexagonalarch.core.usecases.validations.Validator;

public class CustomerIsNotNullValidation implements Validator<Customer> {
    @Override
    public ValidationResult validate(Customer customer) {
        if (customer.getCpf() == null || customer.getCpf().isEmpty() && customer.getEmail() == null ||
            customer.getEmail().isEmpty() && customer.getName() == null || customer.getName().isEmpty()){
            return new ValidationResult(false, "Cliente não identificado, dados estão nulos");
        }
        return new ValidationResult(true, null);
    }
}
