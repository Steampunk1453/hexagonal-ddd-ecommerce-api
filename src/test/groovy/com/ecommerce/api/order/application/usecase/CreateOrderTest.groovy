package com.ecommerce.api.order.application.usecase

import org.javamoney.moneta.Money

import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.Product
import com.ecommerce.api.order.domain.port.OrderRepository
import com.ecommerce.api.order.domain.port.ProductRepository

import spock.lang.Specification
import spock.lang.Subject

class CreateOrderTest extends Specification {

    OrderRepository orderRepository = Mock()

    ProductRepository productRepository = Mock()

    @Subject
    CreateOrder createOrder = new CreateOrder(orderRepository, productRepository)

    def 'should create a new order and return id'() {
        given:
            UUID productId = UUID.randomUUID()
            Integer productQuantity = 1
            Money value = Money.of(new BigDecimal(2.5), "EUR")
            Product product = new Product(productId, "PROD", "product", value)
        when:
            UUID result = createOrder.execute(productId, productQuantity)
        then:
            1 * productRepository.get(_ as UUID) >> product
            1 * orderRepository.save(_ as Order)
            result != null
    }

}
