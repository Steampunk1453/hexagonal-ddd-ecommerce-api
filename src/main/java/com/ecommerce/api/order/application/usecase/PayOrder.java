package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.BusinessException;
import com.ecommerce.api.order.domain.model.payment.CreditCard;
import com.ecommerce.api.order.domain.port.OrderRepository;
import com.ecommerce.api.order.domain.port.PaymentRepository;

public record PayOrder(OrderRepository orderRepository, PaymentRepository paymentRepository) {

    public void execute(final UUID orderId, final CreditCard creditCard) {
        final var order = orderRepository.findById(orderId).orElseThrow(() -> new BusinessException("Order not found"));
        if (paymentRepository.validateCreditCard(creditCard)) {
            paymentRepository.pay(order.totalPrice(), creditCard);
        }
    }

}
