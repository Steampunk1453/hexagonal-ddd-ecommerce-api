package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.BusinessException;
import com.ecommerce.api.order.domain.model.customer.Customer;
import com.ecommerce.api.order.domain.port.CustomerRepository;

public record GetCustomer(CustomerRepository repository) {

    public Customer execute(final UUID id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException("Customer not found"));
    }

}
