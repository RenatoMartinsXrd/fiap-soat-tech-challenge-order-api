package com.hexagonalarch.core.usecases.validations.factory;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.validations.CompositeValidator;
import com.hexagonalarch.core.usecases.validations.Validator;
import com.hexagonalarch.core.usecases.validations.customer.CustomerCpfValidation;
import com.hexagonalarch.core.usecases.validations.customer.CustomerEmailValidation;
import com.hexagonalarch.core.usecases.validations.customer.CustomerIsNotNullValidation;
import com.hexagonalarch.core.usecases.validations.customer.CustomerNameValidation;

import java.util.List;

public class CustomerValidationFactory {

    public static Validator<Customer> getCustomerNotNullValidation() {
        return new CompositeValidator<>(List.of(
                new CustomerIsNotNullValidation()
        ));
    }

    public static Validator<Customer> getNameValidation() {
        return new CompositeValidator<>(List.of(
                new CustomerNameValidation()
        ));
    }

    public static Validator<Customer> getEmailValidation() {
        return new CompositeValidator<>(List.of(
                new CustomerEmailValidation()
        ));
    }

    public static Validator<Customer> getCustomerCpfValidation() {
        return new CompositeValidator<>(List.of(
                new CustomerCpfValidation()
        ));
    }
}
