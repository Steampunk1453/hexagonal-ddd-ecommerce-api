package com.ecommerce.api.order.domain.port;

import java.math.BigDecimal;

import com.ecommerce.api.order.domain.model.payment.CreditCard;

public interface PaymentRepository {

    boolean validateCreditCard(CreditCard creditCard);

    void pay(BigDecimal amount, CreditCard creditCard);

}
