package com.ecommerce.api.order.domain.port;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ecommerce.api.order.domain.model.customer.Customer;

public interface CustomerRepository {

    void save(Customer customer);

    Optional<Customer> findById(UUID id);

    List<Customer> findAll();

}
