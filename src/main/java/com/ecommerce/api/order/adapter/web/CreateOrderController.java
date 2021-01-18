package com.ecommerce.api.order.adapter.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.adapter.web.request.CreateOrderRequest;
import com.ecommerce.api.order.application.usecase.CreateOrder;

@RestController
public class CreateOrderController {

    private final CreateOrder createOrder;

    public CreateOrderController(CreateOrder createOrder) {
        this.createOrder = createOrder;
    }

    @PostMapping("/orders")
    ResponseEntity<UUID> create(@RequestBody final CreateOrderRequest request) {
        final UUID id = createOrder.execute(request.productId(), request.quantity());
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

}
