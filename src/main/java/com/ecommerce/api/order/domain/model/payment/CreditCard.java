package com.ecommerce.api.order.domain.model.payment;

import java.util.Date;

import com.ecommerce.api.order.adapter.web.request.PayOrderRequest;

public record CreditCard(String cardName, String cardNumber, Date expirationDate, String verificationCode) {

    public static CreditCard toDomain(final PayOrderRequest request) {
        return new CreditCard(
            request.cardName(),
            request.cardNumber(),
            request.expirationDate(),
            request.verificationCode());
    }

}
