package com.ecommerce.api.order.adapter.web.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public final record AddProductRequest(@JsonProperty UUID orderId, @JsonProperty UUID productId, @JsonProperty Integer quantity) {

    @JsonCreator
    public AddProductRequest(@JsonProperty UUID orderId, @JsonProperty UUID productId, @JsonProperty Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "AddProductRequest[" +
            "orderId=" + orderId + ", " +
            "productId=" + productId + ", " +
            "quantity=" + quantity + ']';
    }

}
