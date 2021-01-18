package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.port.OrderRepository;

public record DeleteOrder(OrderRepository repository) {

    public void execute(UUID uuid) {
       repository.delete(uuid);
    }

}
