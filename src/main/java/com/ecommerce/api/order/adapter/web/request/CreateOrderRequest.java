package com.ecommerce.api.order.adapter.web.request;

import java.util.UUID;

public record CreateOrderRequest(UUID customerId, UUID productId, Integer quantity) {

}
