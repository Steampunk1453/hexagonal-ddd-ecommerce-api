package com.ecommerce.api.order.domain.port;

import java.math.BigDecimal;

import com.ecommerce.api.order.domain.model.payment.CreditCard;

public interface PaymentRepository {

    boolean pay(BigDecimal money, CreditCard creditCard);

}
