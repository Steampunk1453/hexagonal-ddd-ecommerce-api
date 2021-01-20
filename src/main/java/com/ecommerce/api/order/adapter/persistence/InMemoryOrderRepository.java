package com.ecommerce.api.order.adapter.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ecommerce.api.order.domain.model.Order;
import com.ecommerce.api.order.domain.port.OrderRepository;

@Component
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<UUID, Order> orders = new HashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.id().value(), order);
    }

    @Override
    public void update(Order order) {
        orders.put(order.id().value(), order);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public void delete(UUID id) {
        orders.remove(id);
    }

}
