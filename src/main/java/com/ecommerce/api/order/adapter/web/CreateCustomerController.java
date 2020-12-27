package com.ecommerce.api.order.adapter.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.adapter.web.request.CreateCustomerRequest;
import com.ecommerce.api.order.adapter.web.request.CreateOrderRequest;
import com.ecommerce.api.order.application.usecase.CreateCustomer;
import com.ecommerce.api.order.application.usecase.CreateOrder;

@RestController
@RequestMapping("/customers")
public class CreateCustomerController {

    private final CreateCustomer createCustomer;

    public CreateCustomerController(CreateCustomer createCustomer) {
        this.createCustomer = createCustomer;
    }

    @PostMapping
    ResponseEntity<UUID> create(@RequestBody final CreateCustomerRequest request) {
        UUID customerId = createCustomer.execute(request.name(), request.surname(), request.address());
        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }

}