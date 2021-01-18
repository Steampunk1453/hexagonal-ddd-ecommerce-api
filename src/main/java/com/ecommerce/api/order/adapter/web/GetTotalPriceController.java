package com.ecommerce.api.order.adapter.web;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.application.usecase.GetTotalPrice;

@RestController
public class GetTotalPriceController {

    private final GetTotalPrice getTotalPrice;

    public GetTotalPriceController(GetTotalPrice getTotalPrice) {
        this.getTotalPrice = getTotalPrice;
    }

    @GetMapping("/orders/price/{orderId}")
    public BigDecimal getTotalPrice(@PathVariable("orderId") final UUID orderId) {
        return getTotalPrice.execute(orderId);
    }

}
