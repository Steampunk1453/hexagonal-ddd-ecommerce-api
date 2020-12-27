package com.ecommerce.api.order.domain.port;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.Customer;

public interface CustomerRepository {

    void save(Customer customer);

    Customer get(UUID id);

}
