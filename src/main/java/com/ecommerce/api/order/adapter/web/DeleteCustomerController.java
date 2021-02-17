package com.ecommerce.api.order.adapter.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.application.usecase.DeleteCustomer;

@RestController
public class DeleteCustomerController {

    private final DeleteCustomer deleteCustomer;

    public DeleteCustomerController(DeleteCustomer deleteCustomer) {
        this.deleteCustomer = deleteCustomer;
    }

    @DeleteMapping("/customers/{customerId}/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("customerId") final UUID customerId, @PathVariable("orderId") final UUID orderId) {
        deleteCustomer.execute(customerId, orderId);
    }

}
