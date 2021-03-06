package com.ecommerce.api.order.adapter.web;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.adapter.web.response.OrderResponse;
import com.ecommerce.api.order.application.usecase.GetAllOrders;

@RestController
public class GetOrdersController {

    private final GetAllOrders getAllOrders;

    public GetOrdersController(GetAllOrders getAllOrders) {
        this.getAllOrders = getAllOrders;
    }

    @GetMapping("/orders")
    public List<OrderResponse> getAll() {
        final var orders = getAllOrders.execute();
        return orders.stream()
            .filter(Objects::nonNull)
            .map(OrderResponse::toResponse)
            .collect(Collectors.toList());
    }

}
