package com.ecommerce.api.order.application.usecase

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money

import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.port.PaymentRepository
import com.ecommerce.api.order.domain.model.CreditCard
import com.ecommerce.api.order.domain.port.OrderRepository
import com.ecommerce.api.order.domain.model.Product

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class PayOrderTest extends Specification {

    OrderRepository orderRepository = Mock()

    PaymentRepository paymentRepository = Mock()

    @Subject
    PayOrder payOrder = new PayOrder(orderRepository, paymentRepository)

    @Unroll
    def 'should pay an order with credit card with paid: #isPaid'() {
        given:
            UUID id = UUID.randomUUID()
            Money value = Money.of(new BigDecimal(2.5), "EUR")
            Product product = new Product(id, "product", value)
            CreditCard creditCard = new CreditCard()
        when:
            payOrder.execute(id, creditCard)
        then:
            1 * orderRepository.get(_ as UUID) >> new Order(id, product)
            1 * paymentRepository.pay(_ as MonetaryAmount, _ as CreditCard) >> isPaid
            times * orderRepository.save(_ as Order)
        where:
            isPaid || times
            true   || 1
            false  || 0
    }

}
