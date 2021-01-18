package com.ecommerce.api.order.adapter.persistence;

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
    public Customer get(UUID id) {
        return null;
    }
}
