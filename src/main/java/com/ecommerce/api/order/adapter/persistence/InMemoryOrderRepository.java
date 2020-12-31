package com.ecommerce.api.order.adapter.persistence;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ecommerce.api.order.domain.model.Order;
import com.ecommerce.api.order.domain.port.OrderRepository;

@Component
public class InMemoryOrderRepository implements OrderRepository {

    @Override
    public void save(Order order) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public Order get(UUID uuid) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
