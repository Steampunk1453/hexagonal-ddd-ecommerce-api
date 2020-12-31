package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.port.OrderRepository;
import com.ecommerce.api.order.domain.port.ProductRepository;

public record AddProduct(OrderRepository orderRepository, ProductRepository productRepository) {

    public void execute(final UUID orderId, final UUID productId, Integer quantity) {
        final var order= orderRepository.get(orderId);
        final var product= productRepository.get(productId);
        order.addProduct(product, quantity);
        orderRepository.update(order);
    }

}
