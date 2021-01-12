package com.ecommerce.api.order.domain.model.discount;

import java.math.BigDecimal;

import com.ecommerce.api.order.domain.model.Product;

public interface PriceCalculatorService {
    BigDecimal calculate(Product product, Integer quantity);
}
