package com.ecommerce.api.order.adapter.web.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.ecommerce.api.order.domain.model.Order;
import com.ecommerce.api.order.domain.model.OrderItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GetOrderResponse(@JsonProperty("id") UUID id, @JsonProperty("orderItems") List<OrderItem> orderItems,
                               @JsonProperty("totalPrice") BigDecimal totalPrice) {

    public static GetOrderResponse toResponse(final Order order) {
        return new GetOrderResponse(
            order.id().value(),
            order.orderItems(),
            order.totalPrice());
    }

}
