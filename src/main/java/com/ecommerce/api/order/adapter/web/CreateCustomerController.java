package com.ecommerce.api.order.adapter.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.adapter.web.request.CreateCustomerRequest;
import com.ecommerce.api.order.application.usecase.CreateCustomer;
import com.ecommerce.api.order.domain.model.customer.Address;

@RestController
public class CreateCustomerController {

    private final CreateCustomer createCustomer;

    public CreateCustomerController(CreateCustomer createCustomer) {
        this.createCustomer = createCustomer;
    }

    @PostMapping("/customers")
    ResponseEntity<UUID> create(@RequestBody final CreateCustomerRequest request) {
        final var customerId = createCustomer.execute(request.name(), request.surname(), Address.toDomain(request.address()));
        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }

}
