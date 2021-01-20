package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.BusinessException;
import com.ecommerce.api.order.domain.model.Order;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record GetOrder(OrderRepository repository) {

    public Order execute(UUID id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException("Order not found"));
    }

}
