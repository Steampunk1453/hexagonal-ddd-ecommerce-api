package com.ecommerce.api.order.domain.model.discount;

public record Discount(boolean isActive, String productCode, DiscountStrategy discountStrategy) {

}

