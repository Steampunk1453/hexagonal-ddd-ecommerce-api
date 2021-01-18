package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.customer.Customer;
import com.ecommerce.api.order.domain.port.CustomerRepository;

public record GetCustomer(CustomerRepository repository) {

    public Customer execute(UUID id) {
        return repository.get(id);
    }

}
