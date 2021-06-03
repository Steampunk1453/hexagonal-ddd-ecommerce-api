package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.BusinessException;
import com.ecommerce.api.order.domain.model.Order;
import com.ecommerce.api.order.domain.model.OrderId;
import com.ecommerce.api.order.domain.model.discount.PriceCalculatorService;
import com.ecommerce.api.order.domain.port.OrderRepository;
import com.ecommerce.api.order.domain.port.ProductRepository;

public record CreateOrder(OrderRepository orderRepository,
                          ProductRepository productRepository,
                          PriceCalculatorService priceCalculatorService) {

    public OrderId execute(final UUID productId, final Integer quantity) {
        final var orderId = OrderId.of(UUID.randomUUID());
        final var product = productRepository.findById(productId).orElseThrow(() -> new BusinessException("Product not found"));
        final var itemPrice = priceCalculatorService.calculate(product, quantity);
        final var order = new Order(orderId, product, quantity, itemPrice);
        orderRepository.save(order);
        return order.id();
    }

}
