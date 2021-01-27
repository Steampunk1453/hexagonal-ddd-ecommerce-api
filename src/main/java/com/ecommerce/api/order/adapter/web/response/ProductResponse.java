package com.ecommerce.api.order.adapter.web.response;

import java.math.BigDecimal;
import java.util.UUID;

import com.ecommerce.api.order.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(@JsonProperty UUID id, @JsonProperty String code, @JsonProperty String description, @JsonProperty BigDecimal price) {

    public static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.id(), product.code(), product.description(), product.price());
    }

}
