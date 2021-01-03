package com.ecommerce.api.order.domain.port;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ecommerce.api.order.application.discount.MarketingDiscountStrategy;
import com.ecommerce.api.order.domain.model.discount.Discount;

@Component
public class InMemoryDiscountRepository implements DiscountRepository {

    private final Map<String, Discount> discounts = new HashMap<>();

    @Override
    public void save(Discount discount) {
        MarketingDiscountStrategy marketingDiscountStrategy = new MarketingDiscountStrategy();
        discounts.put("STICKER", new Discount("STICKER", true, marketingDiscountStrategy));
    }

    @Override
    public Discount get(String code) {
        return discounts.get("STICKER");
    }

}
