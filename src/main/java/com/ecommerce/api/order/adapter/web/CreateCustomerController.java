package com.ecommerce.api.order.adapter.web;

import com.ecommerce.api.order.adapter.web.request.CreateCustomerRequest;
import com.ecommerce.api.order.application.usecase.CreateCustomer;
import com.ecommerce.api.order.domain.model.customer.Address;
import com.ecommerce.api.order.domain.model.customer.PersonalData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CreateCustomerController {

    private final CreateCustomer createCustomer;

    public CreateCustomerController(CreateCustomer createCustomer) {
        this.createCustomer = createCustomer;
    }

    @PostMapping("/customers")
    ResponseEntity<UUID> create(@RequestBody final CreateCustomerRequest request) {
        final var customerId = createCustomer.execute(PersonalData.toDomain(request.personalData()), Address.toDomain(request.address()), request.orderId());
        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }

}
