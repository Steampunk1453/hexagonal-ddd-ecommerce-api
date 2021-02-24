package com.ecommerce.api.order.adapter.web.response;

import static com.ecommerce.api.order.adapter.web.response.OrderItemResponse.toOrderItemsResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.ecommerce.api.order.domain.model.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderResponse(@JsonProperty UUID id, @JsonProperty List<OrderItemResponse> orderItems,
                            @JsonProperty BigDecimal totalPrice) {

    public static OrderResponse toResponse(final Order order) {
        return new OrderResponse(
            order.id().value(),
            toOrderItemsResponse(order.orderItems()),
            order.totalPrice());
    }

}
