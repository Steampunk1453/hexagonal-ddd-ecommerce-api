package com.ecommerce.api.order.domain.model;

import java.math.BigDecimal;

public record OrderItem(Product product, Integer quantity, BigDecimal price) {

}



