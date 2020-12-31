package com.ecommerce.api.order.adapter.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.adapter.web.request.CreateOrderRequest;
import com.ecommerce.api.order.application.usecase.CreateOrder;

@RestController
@RequestMapping("/orders")
public class CreateOrderController {

    private final CreateOrder createOrder;

    public CreateOrderController(CreateOrder createOrder) {
        this.createOrder = createOrder;
    }

    @PostMapping
    ResponseEntity<String> create(@RequestBody final CreateOrderRequest createOrderRequest) {
         createOrder.execute(createOrderRequest.productId(), createOrderRequest.quantity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
