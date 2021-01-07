package com.ecommerce.api.order.domain.model.discount;

public record Discount(boolean isActive, DiscountStrategy discountStrategy) {

}

