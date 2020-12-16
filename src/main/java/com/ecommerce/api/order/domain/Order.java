package com.ecommerce.api.order.domain;

import java.util.UUID;

import com.ecommerce.api.order.domain.product.Product;

public class Order {

    private UUID id;

    private Product product;

    public Order(UUID randomUUID, Product product) {
        id = randomUUID;
        this.product = product;
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
