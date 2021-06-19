package com.ecommerce.api.order.domain.model;

import static java.util.Collections.singletonList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.ecommerce.api.order.domain.model.customer.Customer;

public class Order {

    private final OrderId id;

    private final List<OrderItem> orderItems;

    private BigDecimal totalPrice;

    private Customer customer;

    public Order(final OrderId id, final Product product, final Integer quantity, final BigDecimal itemPrice) {
        this.id = id;
        this.orderItems = new ArrayList<>(singletonList(new OrderItem(product, quantity, itemPrice)));
        this.totalPrice = itemPrice;
    }

    public OrderId id() {
        return id;
    }

    public List<OrderItem> orderItems() {
        return orderItems;
    }

    public BigDecimal totalPrice() {
        return totalPrice;
    }

    public void addProduct(final Product product, final Integer quantity, final BigDecimal itemPrice) {
        validateProduct(product);
        handleUpdateProduct(product, quantity, itemPrice);
        totalPrice = totalPrice.add(itemPrice);
    }

    public void removeProduct(final UUID productId) {
        final var orderItem = getOrderItem(productId);
        orderItems.remove(orderItem);
        totalPrice = totalPrice.subtract(orderItem.price());
    }

    public void addCustomer(final Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void removeCustomer() {
        this.customer = null;
    }

    private OrderItem getOrderItem(final UUID productId) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.product().id()
                        .equals(productId))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Product with " + productId + " doesn't exist"));
    }

    private void validateProduct(final Product product) {
        if (product == null) {
            throw new BusinessException("The product cannot be null");
        }
    }

    private void handleUpdateProduct(Product product, Integer quantity, BigDecimal itemPrice) {
        boolean isUpdate = false;
        for (int i = 0, orderItemsSize = orderItems.size(); i < orderItemsSize; i++) {
            OrderItem orderItem = orderItems.get(i);
            isUpdate = isUpdate(product, orderItem);
            if (isUpdate) {
                updateProduct(orderItem, quantity, itemPrice);
            }
        }
        if (!isUpdate) {
            orderItems.add(new OrderItem(product, quantity, itemPrice));
        }
    }

    private boolean isUpdate(Product product, OrderItem orderItem) {
        return orderItem.product().id().equals(product.id());
    }

    private void updateProduct(final OrderItem orderItem, final Integer quantity, final BigDecimal itemPrice) {
        var newOrderItem = new OrderItem(orderItem.product(), orderItem.quantity() + quantity,
                orderItem.price().add(itemPrice));
        orderItems.remove(orderItem);
        orderItems.add(newOrderItem);
    }

}
