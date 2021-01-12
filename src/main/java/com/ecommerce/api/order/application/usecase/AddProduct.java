package com.ecommerce.api.order.application.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import com.ecommerce.api.order.domain.model.discount.PriceCalculatorService;
import com.ecommerce.api.order.domain.port.OrderRepository;
import com.ecommerce.api.order.domain.port.ProductRepository;

public record AddProduct(OrderRepository orderRepository,
                         ProductRepository productRepository,
                         PriceCalculatorService priceCalculatorService) {

    public void execute(final UUID orderId, final UUID productId, Integer quantity) {
        final var order= orderRepository.get(orderId);
        final var product= productRepository.get(productId);
        BigDecimal itemPrice = priceCalculatorService.calculate(product, quantity);
        order.addProduct(product, quantity, itemPrice);
        orderRepository.update(order);
    }

}
