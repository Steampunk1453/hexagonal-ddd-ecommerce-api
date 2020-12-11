package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.Order;
import com.ecommerce.api.order.domain.Product;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record CreateOrder(OrderRepository orderRepository) {

    public UUID execute(Product product) {
        final var order = new Order(UUID.randomUUID(), product);
        orderRepository.save(order);
        return order.getId();
    }

}
