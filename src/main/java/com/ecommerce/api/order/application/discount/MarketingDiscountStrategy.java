package com.ecommerce.api.order.application.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.model.discount.DiscountStrategy;

public record MarketingDiscountStrategy(Integer minimumQuantity) implements DiscountStrategy {

    @Override
    public boolean isApplicable(Integer quantity) {
        return quantity > minimumQuantity;
    }

    @Override
    public BigDecimal apply(Product product, Integer quantity) {
        return calculatePrice(product, quantity);
    }

    private BigDecimal calculatePrice(Product product, Integer quantity) {
        final boolean isEven = quantity % 2 == 0;
        if (isEven) {
            return calculatePriceWithEvenQuantity(product, quantity);
        } else {
            return calculatePriceWithOddQuantity(product, quantity);
        }
    }

    private BigDecimal calculatePriceWithEvenQuantity(Product product, Integer quantity) {
        return product.value().getNumberStripped().divide(new BigDecimal(2), 2, RoundingMode.CEILING)
            .multiply(BigDecimal.valueOf(quantity));
    }

    private BigDecimal calculatePriceWithOddQuantity(Product product, Integer quantity) {
        return product.value().getNumberStripped().divide(new BigDecimal(2), 2, RoundingMode.CEILING)
            .multiply(BigDecimal.valueOf(quantity + 1));
    }

}