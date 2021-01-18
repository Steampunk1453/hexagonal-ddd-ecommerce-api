package com.ecommerce.api.order.adapter.web.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.ecommerce.api.order.domain.model.Order;
import com.ecommerce.api.order.domain.model.OrderItem;

public record GetOrderResponse(UUID id, List<OrderItem> orderItems, BigDecimal totalPrice) {

    public static GetOrderResponse toResponse(final Order order) {
        return new GetOrderResponse(
            order.getId(),
            order.getOrderItems(),
            order.getTotalPrice());
    }

}
