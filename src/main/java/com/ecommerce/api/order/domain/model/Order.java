package com.ecommerce.api.order.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID id;

    private Product product;

    private List<OrderItem> orderItems;

    private BigDecimal price;

    public Order(final UUID id, final Product product) {
        this.id = id;
        this.orderItems = new ArrayList<>(Collections.singletonList(new OrderItem(product)));;
        this.product = product;
        this.price =  product.value().getNumberStripped();
    }

    public UUID getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void addProduct(final Product product) {
        validateProduct(product);
        orderItems.add(new OrderItem(product));
        price = price.add(product.value().getNumberStripped());
    }

    public void remove(final UUID id) {
        final OrderItem orderItem = getOrderItem(id);
        orderItems.remove(orderItem);
        price = price.subtract(orderItem.product().value().getNumberStripped());
    }

    private OrderItem getOrderItem(final UUID id) {
        return orderItems.stream()
            .filter(orderItem -> orderItem.product().id()
                .equals(id))
            .findFirst()
            .orElseThrow(() -> new DomainException("Product with " + id + " doesn't exist."));
    }

    private void validateProduct(final Product product) {
        if (product == null) {
            throw new DomainException("The product cannot be null");
        }
    }


}
