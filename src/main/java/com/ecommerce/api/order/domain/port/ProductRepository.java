package com.ecommerce.api.order.domain.port;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.Product;

public interface ProductRepository {

    void save(Product product);

    Product get(UUID id);

}
