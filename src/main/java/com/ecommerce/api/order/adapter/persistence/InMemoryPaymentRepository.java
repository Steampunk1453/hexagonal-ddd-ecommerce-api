package com.ecommerce.api.order.adapter.persistence;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ecommerce.api.order.domain.model.payment.CreditCard;
import com.ecommerce.api.order.domain.port.PaymentRepository;

@Component
public class InMemoryPaymentRepository implements PaymentRepository {

    @Override
    public boolean pay(BigDecimal money, CreditCard creditCard) {
        return false;
    }

}
