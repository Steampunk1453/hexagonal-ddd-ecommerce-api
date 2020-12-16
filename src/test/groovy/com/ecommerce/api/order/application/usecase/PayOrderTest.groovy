package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.Order
import com.ecommerce.api.order.domain.payment.port.PaymentRepository
import com.ecommerce.api.order.domain.payment.CreditCard
import com.ecommerce.api.order.domain.port.OrderRepository
import com.ecommerce.api.order.domain.product.Product

import spock.lang.Specification
import spock.lang.Subject

class PayOrderTest extends Specification {

    OrderRepository orderRepository = Mock()

    PaymentRepository paymentRepository = Mock()

    @Subject
    PayOrder payOrder = new PayOrder(repository)

    def 'should pay an order with credit card'() {
        given:
            UUID id = UUID.randomUUID()
            CreditCard creditCard = new CreditCard()
            Product product = new Product()
        when:
            payOrder.execute(id, creditCard)
        then:
            1 * orderRepository.get(_ as UUID) >> new Order(id, product)


    }

}
