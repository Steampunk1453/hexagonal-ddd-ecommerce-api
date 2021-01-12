package com.ecommerce.api.order.application.discount;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ecommerce.api.order.domain.model.discount.PriceCalculatorService;
import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.model.discount.Discount;
import com.ecommerce.api.order.domain.port.DiscountRepository;

@Service
public record DefaultPriceCalculatorService(DiscountRepository discountRepository) implements PriceCalculatorService {

    @Override
    public BigDecimal calculate(Product product, Integer quantity) {
        var discounts = discountRepository.getAll();

        return discounts.values().stream()
            .filter(discount -> isApplicable(product, quantity, discount))
            .map(discount -> discount.discountStrategy().apply(product, quantity))
            .reduce(BigDecimal::min)
            .orElseGet(() -> calculateWithoutDiscount(product.price(), quantity));
    }

    private boolean isApplicable(Product product, Integer quantity, Discount discount) {
        return discount.isActive() && discount.discountStrategy().isEligible(quantity) && discount.productCode().equals(product.code());
    }

    private BigDecimal calculateWithoutDiscount(BigDecimal productPrice, Integer quantity) {
        return productPrice.multiply(BigDecimal.valueOf(quantity));
    }

}
