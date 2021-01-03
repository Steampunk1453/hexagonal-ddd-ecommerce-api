package com.ecommerce.api.order.application.discount;

import java.math.BigDecimal;

import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.model.discount.DiscountStrategy;

public class FinancialDiscountStrategy implements DiscountStrategy {

    @Override
    public BigDecimal apply(Product product, Integer quantity) {
        return null;
    }
}
