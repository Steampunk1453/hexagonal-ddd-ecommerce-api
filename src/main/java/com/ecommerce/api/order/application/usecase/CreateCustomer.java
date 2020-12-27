package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.Address;
import com.ecommerce.api.order.domain.model.Customer;
import com.ecommerce.api.order.domain.port.CustomerRepository;

public record CreateCustomer(CustomerRepository repository) {

    public UUID execute(String name, String surname, Address address) {
        final var customer = new Customer(UUID.randomUUID(), name, surname, address);
        repository.save(customer);
        return customer.uuid();
    }

}
