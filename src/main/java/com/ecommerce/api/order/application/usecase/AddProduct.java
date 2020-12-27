package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record AddProduct(OrderRepository repository) {

    public void execute(final UUID id, final Product product) {
        final var order= repository.get(id);
        order.addProduct(product);
        repository.update(order);
    }

}
