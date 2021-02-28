package com.ecommerce.api.order.adapter.web.request;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final record CreateOrderRequest(@JsonProperty UUID productId, @JsonProperty Integer quantity) {

    @JsonCreator
    public CreateOrderRequest(@JsonProperty UUID productId, @JsonProperty Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest[" +
            "productId=" + productId + ", " +
            "quantity=" + quantity + ']';
    }

}
