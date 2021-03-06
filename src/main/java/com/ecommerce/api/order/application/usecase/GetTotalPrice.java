package com.ecommerce.api.order.application.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import com.ecommerce.api.order.domain.model.BusinessException;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record GetTotalPrice(OrderRepository repository) {

    public BigDecimal execute(final UUID id) {
        final var order = repository.findById(id).orElseThrow(() -> new BusinessException("Order not found"));
        return order.totalPrice();
    }

}
