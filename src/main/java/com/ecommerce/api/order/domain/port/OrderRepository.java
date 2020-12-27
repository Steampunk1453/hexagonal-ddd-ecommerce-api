package com.ecommerce.api.order.domain.port;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.Order;

public interface OrderRepository {

    void save(Order order);

    void update(Order order);

    Order get(UUID uuid);

    void delete(UUID uuid);

}
