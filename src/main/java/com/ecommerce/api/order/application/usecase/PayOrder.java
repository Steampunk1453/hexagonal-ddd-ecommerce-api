package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.payment.CreditCard;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record PayOrder(OrderRepository repository) {

    public void execute(UUID uuid, CreditCard creditCard) {

    }
}
