package com.hexagonalarch.core.usecases.strategy.customer.factories;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.usecases.strategy.customer.*;
import com.hexagonalarch.core.usecases.strategy.customer.strategies.CreateOrIdentifyCustomerCpfStrategy;
import com.hexagonalarch.core.usecases.strategy.customer.strategies.CreateOrIdentifyCustomerWithoutCpfStrategy;
import com.hexagonalarch.core.usecases.strategy.customer.strategies.DefaultCustomerStrategy;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import com.hexagonalarch.core.usecases.validations.factory.CustomerValidationFactory;
import com.hexagonalarch.shared.exception.BusinessException;

public class IdentifyOrCreateCustomerFactory {
    private final CustomerGatewayPort customerRepository;

    public IdentifyOrCreateCustomerFactory(CustomerGatewayPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerStrategy getStrategy(Customer customer) {
        ValidationResult customerNotNull = CustomerValidationFactory.getCustomerNotNullValidation().validate(customer);
        boolean isValidEmail = CustomerValidationFactory.getEmailValidation().validate(customer).isValid();
        boolean isValidName = CustomerValidationFactory.getNameValidation().validate(customer).isValid();
        boolean isValidCpf = CustomerValidationFactory.getCustomerCpfValidation().validate(customer).isValid();

        if(!customerNotNull.isValid()){
            throw new BusinessException(customerNotNull.getMessage());
        }
        if (!isValidCpf && isValidEmail && isValidName) {
            return new CreateOrIdentifyCustomerWithoutCpfStrategy(customerRepository);
        }

        if(isValidCpf){
            return new CreateOrIdentifyCustomerCpfStrategy(customerRepository);
        }

       return new DefaultCustomerStrategy();
    }
}