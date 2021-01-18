package com.ecommerce.api.order.adapter.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.adapter.web.request.AddProductRequest;
import com.ecommerce.api.order.application.usecase.AddProduct;

@RestController
public class AddProductController {

    private final AddProduct addProduct;

    public AddProductController(AddProduct addProduct) {
        this.addProduct = addProduct;
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    void add(@RequestBody final AddProductRequest request) {
        addProduct.execute(request.orderId(), request.productId(), request.quantity());
    }

}
