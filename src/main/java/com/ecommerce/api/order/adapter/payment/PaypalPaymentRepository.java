package com.ecommerce.api.order.adapter.payment;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.ecommerce.api.order.domain.model.payment.CreditCard;
import com.ecommerce.api.order.domain.port.PaymentRepository;

@Repository
public class PaypalPaymentRepository implements PaymentRepository {

    @Override
    public boolean validateCreditCard(CreditCard creditCard) {
        /*
           Payment integration implementation comes here
         */
        return true;
    }

    @Override
    public void pay(BigDecimal amount, CreditCard creditCard) {
        /*
          Paypal integration implementation comes here
         */
    }

}
