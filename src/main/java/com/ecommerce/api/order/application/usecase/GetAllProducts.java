package com.ecommerce.api.order.application.usecase;

import java.util.List;

import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.port.ProductRepository;

public record GetAllProducts(ProductRepository repository) {

    public List<Product> execute() {
        return repository.findAll();
    }

}
