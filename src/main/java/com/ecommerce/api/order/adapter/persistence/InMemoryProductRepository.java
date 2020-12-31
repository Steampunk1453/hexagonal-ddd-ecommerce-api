package com.ecommerce.api.order.adapter.persistence;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.port.ProductRepository;

@Component
public class InMemoryProductRepository implements ProductRepository {

    @Override
    public void save(Product product) {

    }

    @Override
    public Product get(UUID id) {
        return null;
    }
}
