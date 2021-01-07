package com.ecommerce.api.order.domain.model.discount;

import java.math.BigDecimal;

import com.ecommerce.api.order.domain.model.Product;

public interface DiscountStrategy {

    boolean isApplicable(Integer quantity);
    BigDecimal apply(Product product, Integer quantity);

}
