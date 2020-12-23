package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.payment.CreditCard;
import com.ecommerce.api.order.domain.payment.port.PaymentRepository;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record PayOrder(OrderRepository orderRepository, PaymentRepository paymentRepository) {

    public void execute(UUID uuid, CreditCard creditCard) {
        var order = orderRepository.get(uuid);
        if(paymentRepository.pay(order.getProduct().value(), creditCard)) {
            orderRepository.save(order);
        }
    }

}
