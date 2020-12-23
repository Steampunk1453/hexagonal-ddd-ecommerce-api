package com.ecommerce.api.order.domain.payment.port;

import org.javamoney.moneta.Money;

import com.ecommerce.api.order.domain.payment.CreditCard;

public interface PaymentRepository {

    boolean pay(Money money, CreditCard creditCard);
}
