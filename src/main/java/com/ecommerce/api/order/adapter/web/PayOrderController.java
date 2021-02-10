package com.ecommerce.api.order.adapter.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.adapter.web.request.PayOrderRequest;
import com.ecommerce.api.order.application.usecase.PayOrder;
import com.ecommerce.api.order.domain.model.payment.CreditCard;

@RestController
public class PayOrderController {

    private final PayOrder payOrder;

    public PayOrderController(PayOrder payOrder) {
        this.payOrder = payOrder;
    }

    @PostMapping("payments/{orderId}")
    @ResponseStatus(HttpStatus.CREATED)
    void pay(@PathVariable("orderId") final UUID orderId, @RequestBody final PayOrderRequest request) {
        payOrder.execute(orderId, CreditCard.toDomain(request));
    }

}
