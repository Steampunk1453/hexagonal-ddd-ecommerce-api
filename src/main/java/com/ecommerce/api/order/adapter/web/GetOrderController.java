package com.ecommerce.api.order.adapter.web;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.adapter.web.response.GetOrderResponse;
import com.ecommerce.api.order.application.usecase.GetOrder;

@RestController
public class GetOrderController {

    private final GetOrder getOrder;

    public GetOrderController(GetOrder getOrder) {
        this.getOrder = getOrder;
    }

    @GetMapping("/orders/{orderId}")
    public GetOrderResponse get(@PathVariable("orderId") final UUID orderId) {
        var order = getOrder.execute(orderId);
        return GetOrderResponse.toResponse(order);
    }

}
