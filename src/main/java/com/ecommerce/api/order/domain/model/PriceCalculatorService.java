package com.ecommerce.api.order.domain.model;

import java.math.BigDecimal;

public interface PriceCalculatorService {
    BigDecimal calculate(Product product, Integer quantity);
}
