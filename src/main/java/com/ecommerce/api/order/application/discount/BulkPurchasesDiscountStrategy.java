package com.ecommerce.api.order.application.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.model.discount.DiscountStrategy;

public record BulkPurchasesDiscountStrategy(Integer minimumQuantity, BigDecimal discountedApplied) implements DiscountStrategy {

    @Override
    public boolean isEligible(Integer quantity) {
        return quantity >= minimumQuantity;
    }

    @Override
    public BigDecimal apply(Product product, Integer quantity) {
        return product.price().subtract(discountedApplied).multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.CEILING);
    }

}
