package com.ecommerce.api.order.application.usecase;

import java.util.List;

import com.ecommerce.api.order.domain.model.Order;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record GetAllOrders(OrderRepository repository) {

    public List<Order> execute() {
        return repository.findAll();
    }

}
