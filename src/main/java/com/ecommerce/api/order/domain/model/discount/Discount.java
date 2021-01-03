package com.ecommerce.api.order.domain.model.discount;

public record Discount(String productCode, boolean isActive, DiscountStrategy discountStrategy) {
}
