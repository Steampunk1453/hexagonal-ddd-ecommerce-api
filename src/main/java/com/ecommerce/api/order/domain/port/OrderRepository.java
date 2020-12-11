package com.ecommerce.api.order.domain.port;

import com.ecommerce.api.order.domain.Order;

public interface OrderRepository {

    void save(Order order);

}
