package com.ecommerce.api.order.domain.model.payment;

import java.util.Date;

import com.ecommerce.api.order.adapter.web.request.PayOrderRequest;

public record CreditCard(String cardholder, String cardNumber, Date expirationDate, String verificationCode) {

    public static CreditCard toDomain(final PayOrderRequest request) {
        return new CreditCard(
            request.cardholder(),
            request.cardNumber(),
            request.expirationDate(),
            request.verificationCode());
    }

}
