package com.ecommerce.api.order.domain.model;

import java.util.UUID;

import org.springframework.lang.NonNull;

public record OrderId(@NonNull UUID value) {

    public static OrderId of(final UUID articleId) {
        return new OrderId(articleId);
    }

}
