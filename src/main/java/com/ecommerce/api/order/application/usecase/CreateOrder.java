package com.ecommerce.api.order.application.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import com.ecommerce.api.order.domain.model.Order;
import com.ecommerce.api.order.domain.model.PriceCalculatorService;
import com.ecommerce.api.order.domain.port.OrderRepository;
import com.ecommerce.api.order.domain.port.ProductRepository;

public record CreateOrder(OrderRepository orderRepository,
                          ProductRepository productRepository,
                          PriceCalculatorService priceCalculatorService ) {

    public UUID execute(UUID productId, Integer quantity) {
        final var product = productRepository.get(productId);
        BigDecimal price = priceCalculatorService.calculate(product, quantity);
        final var order = new Order(UUID.randomUUID(), product, quantity, price);
        orderRepository.save(order);

        return order.getId();
    }

}
