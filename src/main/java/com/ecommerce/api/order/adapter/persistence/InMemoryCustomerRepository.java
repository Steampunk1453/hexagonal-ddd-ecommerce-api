package com.ecommerce.api.order.adapter.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ecommerce.api.order.domain.model.customer.Customer;
import com.ecommerce.api.order.domain.port.CustomerRepository;

@Component
public class InMemoryCustomerRepository implements CustomerRepository {

    @Override
    public void save(Customer customer) {

    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return Optional.empty();
    }

}
