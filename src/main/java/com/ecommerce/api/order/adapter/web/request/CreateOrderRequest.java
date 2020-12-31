package com.ecommerce.api.order.adapter.web.request;

import java.util.UUID;

public record CreateOrderRequest(UUID productId, Integer quantity) {

}
