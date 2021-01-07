package com.ecommerce.api.order.application.discount;

import java.math.BigDecimal;

import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.model.discount.DiscountStrategy;

public record FinancialDiscountStrategy(Integer minimumQuantity, BigDecimal discountedApplied) implements DiscountStrategy {

    @Override
    public boolean isApplicable(Integer quantity) {
        return quantity > minimumQuantity;
    }

    @Override
    public BigDecimal apply(Product product, Integer quantity) {
        return product.value().getNumberStripped().subtract(discountedApplied)
            .multiply(BigDecimal.valueOf(quantity));
    }

}
