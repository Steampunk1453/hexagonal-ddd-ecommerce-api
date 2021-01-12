package com.ecommerce.api.order.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(UUID id, String code, String description, BigDecimal price) {
}
