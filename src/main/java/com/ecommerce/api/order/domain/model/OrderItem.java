package com.ecommerce.api.order.domain.model;

import javax.validation.constraints.Min;

public record OrderItem(Product product, @Min(value = 0, message = "Quantity must be greater than zero") Integer quantity) {

}



