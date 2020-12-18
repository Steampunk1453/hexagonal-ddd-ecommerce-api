package com.ecommerce.api.order.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.ecommerce.api.order.domain.product.Product;

public class Order {

    private UUID id;

    private Product product;

    private List<OrderItem> orderItems;

    private BigDecimal price;

    public Order(UUID randomUUID, Product product) {
        id = randomUUID;
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

    public void addProduct(final Product product) {
    }
}
