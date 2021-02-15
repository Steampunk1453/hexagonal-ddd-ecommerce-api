package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.BusinessException;
import com.ecommerce.api.order.domain.model.discount.PriceCalculatorService;
import com.ecommerce.api.order.domain.port.OrderRepository;
import com.ecommerce.api.order.domain.port.ProductRepository;

public record AddProduct(OrderRepository orderRepository,
                         ProductRepository productRepository,
                         PriceCalculatorService priceCalculatorService) {

    public void execute(final UUID orderId, final UUID productId, final Integer quantity) {
        final var order = orderRepository.findById(orderId).orElseThrow(() -> new BusinessException("Order not found"));
        final var product = productRepository.findById(productId).orElseThrow(() -> new BusinessException("Product not found"));
        final var itemPrice = priceCalculatorService.calculate(product, quantity);
        order.addProduct(product, quantity, itemPrice);
        orderRepository.update(order);
    }

}
