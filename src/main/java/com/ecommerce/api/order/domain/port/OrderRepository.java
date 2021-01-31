package com.ecommerce.api.order.domain.port;

import java.util.Optional;
import java.util.UUID;

import com.ecommerce.api.order.domain.model.Order;

public interface OrderRepository {

    void save(Order order);

    void update(Order order);

    Optional<Order> findById(UUID id);

    void delete(UUID id);

}
