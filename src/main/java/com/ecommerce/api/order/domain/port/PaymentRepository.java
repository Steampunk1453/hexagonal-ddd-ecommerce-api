package com.ecommerce.api.order.domain.port;

import org.javamoney.moneta.Money;

import com.ecommerce.api.order.domain.model.CreditCard;

public interface PaymentRepository {

    boolean pay(Money money, CreditCard creditCard);
}
