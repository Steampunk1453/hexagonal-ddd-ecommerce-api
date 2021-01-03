package com.ecommerce.api.order.application.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.model.discount.DiscountStrategy;

public class MarketingDiscountStrategy implements DiscountStrategy {

    public static final String DISCOUNT_PRODUCT_CODE = "STICKER";

    @Override
    public BigDecimal apply(Product product, Integer quantity) {
        if (DISCOUNT_PRODUCT_CODE.equals(product.code())) {
            return calculatePriceWithDiscount(product, quantity);
        }
        return calculatePrice(product, quantity);
    }

    private BigDecimal calculatePriceWithDiscount(Product product, Integer quantity) {
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

    private BigDecimal calculatePrice(Product product, Integer quantity) {
        return product.value().getNumberStripped().multiply(BigDecimal.valueOf(quantity));
    }

}