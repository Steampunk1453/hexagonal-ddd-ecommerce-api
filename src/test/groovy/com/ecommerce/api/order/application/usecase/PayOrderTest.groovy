package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.OrderProvider
import com.ecommerce.api.order.domain.model.BusinessException
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.payment.CreditCard
import com.ecommerce.api.order.domain.port.OrderRepository
import com.ecommerce.api.order.domain.port.PaymentRepository

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
            Order order = Spy(OrderProvider.buildOrder())
            UUID orderId = UUID.randomUUID()
            CreditCard creditCard = new CreditCard("VISA", "12345678A", new Date(), "123")
        when:
            payOrder.execute(orderId, creditCard)
        then:
            1 * orderRepository.findById(_ as UUID) >> Optional.of(order)
            1 * paymentRepository.pay(_ as BigDecimal, _ as CreditCard) >> isPaid
            times * orderRepository.save(_ as Order)
        where:
            isPaid || times
            true   || 1
            false  || 0
    }

    def 'should throw business exception when pay an order with credit card and OrderRepository returns null'() {
        given:
            UUID orderId = UUID.randomUUID()
            CreditCard creditCard = new CreditCard("VISA", "12345678A", new Date(), "123")
        when:
            payOrder.execute(orderId, creditCard)
        then:
            1 * orderRepository.findById(_ as UUID) >> Optional.ofNullable(null)
            thrown(BusinessException)
    }

}
