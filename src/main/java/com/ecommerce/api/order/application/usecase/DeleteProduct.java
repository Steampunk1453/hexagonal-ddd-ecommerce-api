package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.BusinessException;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record DeleteProduct(OrderRepository repository) {

    public void execute(UUID orderId, UUID productId) {
        final var order = repository.findById(orderId).orElseThrow(() -> new BusinessException("Order not found"));
        order.removeProduct(productId);
        repository.save(order);
    }

}
