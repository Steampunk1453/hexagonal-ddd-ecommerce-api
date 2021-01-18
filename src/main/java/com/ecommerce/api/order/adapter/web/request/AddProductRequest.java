package com.ecommerce.api.order.adapter.web.request;

import java.util.UUID;

public record AddProductRequest(UUID orderId, UUID productId, Integer quantity) {

}
