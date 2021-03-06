package com.ecommerce.api.order.adapter.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UUID> create(@RequestBody final CreateOrderRequest request) {
        final var orderId = createOrder.execute(request.productId(), request.quantity());
        return new ResponseEntity<>(orderId.value(), HttpStatus.CREATED);
    }

}
