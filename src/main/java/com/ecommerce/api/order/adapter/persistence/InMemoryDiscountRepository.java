package com.ecommerce.api.order.adapter.persistence;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ecommerce.api.order.domain.model.discount.Discount;
import com.ecommerce.api.order.domain.port.DiscountRepository;

@Component
public class InMemoryDiscountRepository implements DiscountRepository {

    private final Map<String, Discount> discounts = new HashMap<>();

    @Override
    public void save(String discountCode, Discount discount) {
        discounts.put(discountCode, discount);
    }

    @Override
    public Map<String, Discount> getAll() {
        return discounts;
    }

    @Override
    public Discount get(String code) {
        return discounts.get(code);
    }

    @Override
    public void delete(String code) {
        discounts.remove(code);
    }

}
