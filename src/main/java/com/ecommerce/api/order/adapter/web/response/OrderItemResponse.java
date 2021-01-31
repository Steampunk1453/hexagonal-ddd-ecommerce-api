package com.ecommerce.api.order.adapter.web.response;

import static com.ecommerce.api.order.adapter.web.response.ProductResponse.toProductResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ecommerce.api.order.domain.model.OrderItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderItemResponse(@JsonProperty ProductResponse product, @JsonProperty Integer quantity, @JsonProperty BigDecimal price) {

    public static List<OrderItemResponse> toOrderItemsResponse(List<OrderItem> orderItems) {
        return orderItems.stream()
            .filter(Objects::nonNull)
            .map(OrderItemResponse::toOrderItemResponse)
            .collect(Collectors.toList());
    }

    private static OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(toProductResponse(orderItem.product()), orderItem.quantity(), orderItem.price());
    }

}
