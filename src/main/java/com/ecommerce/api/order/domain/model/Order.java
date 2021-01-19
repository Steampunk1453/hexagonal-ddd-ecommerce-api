package com.ecommerce.api.order.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID id;

    private final List<OrderItem> orderItems;

    private BigDecimal totalPrice;

    public Order(final UUID id, final Product product, final Integer quantity, BigDecimal itemPrice) {
        this.id = id;
        this.orderItems = new ArrayList<>(Collections.singletonList(new OrderItem(product, quantity, itemPrice)));
        this.totalPrice = itemPrice;
    }

    public UUID getId() {
        return id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void addProduct(final Product product, Integer quantity, BigDecimal itemPrice) {
        validateProduct(product);
        orderItems.add(new OrderItem(product, quantity, itemPrice));
        totalPrice = totalPrice.add(itemPrice);
    }

    public void removeProduct(final UUID productId) {
        final var orderItem = getOrderItem(productId);
        orderItems.remove(orderItem);
        totalPrice = totalPrice.subtract(orderItem.price());
    }

    private OrderItem getOrderItem(final UUID productId) {
        return orderItems.stream()
            .filter(orderItem -> orderItem.product().id()
                .equals(productId))
            .findFirst()
            .orElseThrow(() -> new DomainException("Product with " + productId + " doesn't exist"));
    }

    private void validateProduct(final Product product) {
        if (product == null) {
            throw new DomainException("The product cannot be null");
        }
    }

}
