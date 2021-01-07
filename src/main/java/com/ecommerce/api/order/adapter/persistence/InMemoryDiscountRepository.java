package com.ecommerce.api.order.adapter.persistence;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ecommerce.api.order.application.discount.FinancialDiscountStrategy;
import com.ecommerce.api.order.application.discount.MarketingDiscountStrategy;
import com.ecommerce.api.order.domain.model.discount.Discount;
import com.ecommerce.api.order.domain.port.DiscountRepository;

@Component
public class InMemoryDiscountRepository implements DiscountRepository {

    private final Map<String, Discount> discounts = new HashMap<>();

    @Override
    public void save(Discount discountParameters) {
        discounts.put("STICKER", new Discount(true, new MarketingDiscountStrategy(2)));
        discounts.put("TSHIRT",  new Discount(true, new FinancialDiscountStrategy(3, new BigDecimal(1))));
    }

    @Override
    public Discount get(String code) {
        return discounts.get("STICKER");
    }

}
