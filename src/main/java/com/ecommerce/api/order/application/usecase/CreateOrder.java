package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.Order;
import com.ecommerce.api.order.domain.product.Product;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record CreateOrder(OrderRepository repository) {

    public UUID execute(Product product) {
        final var order = new Order(UUID.randomUUID(), product);
        repository.save(order);
        return order.getId();
    }

}
