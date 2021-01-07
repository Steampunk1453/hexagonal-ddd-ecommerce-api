package com.ecommerce.api.order.application;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ecommerce.api.order.domain.model.PriceCalculatorService;
import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.model.discount.Discount;
import com.ecommerce.api.order.domain.model.discount.DiscountStrategy;
import com.ecommerce.api.order.domain.port.DiscountRepository;

@Service
public record DefaultPriceCalculatorService(DiscountRepository discountRepository) implements PriceCalculatorService {

    @Override
    public BigDecimal calculate(Product product, Integer quantity) {
        Discount discount = discountRepository.get(product.code());
        DiscountStrategy discountStrategy = discount.discountStrategy();

        if (discount.isActive() && discountStrategy.isApplicable(quantity)) {
            return discountStrategy.apply(product, quantity);
        }
        return calculateWithoutDiscount(product.value().getNumberStripped(), quantity);
    }

    private BigDecimal calculateWithoutDiscount(BigDecimal productPrice, Integer quantity) {
        return productPrice.multiply(BigDecimal.valueOf(quantity));
    }

}
