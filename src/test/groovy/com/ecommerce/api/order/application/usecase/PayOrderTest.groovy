package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.OrderId
import com.ecommerce.api.order.domain.model.Product
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
            UUID id = UUID.randomUUID()
            BigDecimal price = new BigDecimal(12.50)
            Product product = new Product(id, "BOOK", "product", price)
            Integer productQuantity = 1
            CreditCard creditCard = new CreditCard("VISA", "12345678A", new Date(), "123")
            BigDecimal itemPrice = new BigDecimal(12.50)
        when:
            payOrder.execute(id, creditCard)
        then:
            1 * orderRepository.get(_ as UUID) >> new Order(OrderId.of(id), product, productQuantity, itemPrice)
            1 * paymentRepository.pay(_ as BigDecimal, _ as CreditCard) >> isPaid
            times * orderRepository.save(_ as Order)
        where:
            isPaid || times
            true   || 1
            false  || 0
    }

}
