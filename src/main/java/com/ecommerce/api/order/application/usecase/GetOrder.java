package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.Order;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record GetOrder(OrderRepository repository) {

    public Order execute(UUID uuid) {
        return repository.get(uuid);
    }
}
