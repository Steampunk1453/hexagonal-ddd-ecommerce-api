package com.ecommerce.api.order.domain.port;

import com.ecommerce.api.order.domain.model.discount.Discount;

public interface DiscountRepository {

    void save(Discount discount);

    Discount get(String code);

}
