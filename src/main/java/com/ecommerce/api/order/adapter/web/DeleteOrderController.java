package com.ecommerce.api.order.adapter.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.application.usecase.DeleteOrder;

@RestController
public class DeleteOrderController {

    private final DeleteOrder deleteOrder;

    public DeleteOrderController(DeleteOrder deleteOrder) {
        this.deleteOrder = deleteOrder;
    }

    @DeleteMapping("/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("orderId") final UUID orderId) {
        deleteOrder.execute(orderId);
    }

}
