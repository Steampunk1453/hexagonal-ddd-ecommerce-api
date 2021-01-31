package com.ecommerce.api.order.domain.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

public record OrderItem(Product product, @Min(value = 1, message = "Quantity must be greater than zero") Integer quantity, BigDecimal price) {

}



