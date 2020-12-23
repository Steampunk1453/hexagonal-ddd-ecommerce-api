package com.ecommerce.api.order.application.usecase

import org.javamoney.moneta.Money

import com.ecommerce.api.order.domain.Order
import com.ecommerce.api.order.domain.product.Product
import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Specification
import spock.lang.Subject

class CreateOrderTest extends Specification {

    OrderRepository orderRepository = Mock()

    @Subject
    CreateOrder createOrder = new CreateOrder(orderRepository)

    def 'should create a new order save it and return id'() {
        given:
            UUID id = UUID.randomUUID()
            Money value = Money.of(new BigDecimal(2.5), "EUR")
            Product product = new Product(id, "product", value)
        when:
            UUID result = createOrder.execute(product)
        then:
            1 * orderRepository.save(_ as Order)
            result != null
    }

}
