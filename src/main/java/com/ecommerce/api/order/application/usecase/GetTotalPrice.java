package com.ecommerce.api.order.application.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import com.ecommerce.api.order.domain.port.OrderRepository;

public record GetTotalPrice(OrderRepository repository) {

    public BigDecimal execute(UUID id) {
        var order = repository.get(id);
        return order.getTotalPrice();
    }

}
