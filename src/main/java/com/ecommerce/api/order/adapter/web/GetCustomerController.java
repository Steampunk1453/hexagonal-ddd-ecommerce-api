package com.ecommerce.api.order.adapter.web;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.adapter.web.response.GetCustomerResponse;
import com.ecommerce.api.order.application.usecase.GetCustomer;

@RestController
public class GetCustomerController {

    private final GetCustomer getCustomer;

    public GetCustomerController(GetCustomer getCustomer) {
        this.getCustomer = getCustomer;
    }

    @GetMapping("/customers/{customerId}")
    public GetCustomerResponse get(@PathVariable("customerId") final UUID customerId) {
        final var customer = getCustomer.execute(customerId);
        return GetCustomerResponse.toResponse(customer);
    }

}
