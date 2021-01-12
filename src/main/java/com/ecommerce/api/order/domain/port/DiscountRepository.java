package com.ecommerce.api.order.domain.port;

import java.util.Map;

import com.ecommerce.api.order.domain.model.discount.Discount;

public interface DiscountRepository {

    void save(String discountCode, Discount discount);

    Map<String, Discount> getAll();

    Discount get(String code);

    void delete(String code);

}
