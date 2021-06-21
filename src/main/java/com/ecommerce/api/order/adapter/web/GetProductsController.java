package com.ecommerce.api.order.adapter.web;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.adapter.web.response.ProductResponse;
import com.ecommerce.api.order.application.usecase.GetAllProducts;

@RestController
public class GetProductsController {

    private final GetAllProducts getAllProducts;

    public GetProductsController(GetAllProducts getAllProducts) {
        this.getAllProducts = getAllProducts;
    }

    @GetMapping("/products")
    public List<ProductResponse> getAll() {
        final var products = getAllProducts.execute();
        return products.stream()
            .filter(Objects::nonNull)
            .map(ProductResponse::toProductResponse)
            .collect(Collectors.toList());
    }

}
