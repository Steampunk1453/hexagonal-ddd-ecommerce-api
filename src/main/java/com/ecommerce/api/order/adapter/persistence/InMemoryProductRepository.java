package com.ecommerce.api.order.adapter.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.port.ProductRepository;

@Component
public class InMemoryProductRepository implements ProductRepository {

    private final Map<UUID, Product> products = new HashMap<>();

    @Override
    public void save(Product product) {
        products.put(product.id(), product);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.empty();
    }
}
