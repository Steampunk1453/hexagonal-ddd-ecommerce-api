package com.ecommerce.api.order.application.usecase

import org.javamoney.moneta.Money

import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.PriceCalculatorService
import com.ecommerce.api.order.domain.model.Product
import com.ecommerce.api.order.domain.port.OrderRepository
import com.ecommerce.api.order.domain.port.ProductRepository

import spock.lang.Specification
import spock.lang.Subject

class CreateOrderTest extends Specification {

    OrderRepository orderRepository = Mock()

    ProductRepository productRepository = Mock()

    PriceCalculatorService priceCalculatorService = Mock()

    @Subject
    CreateOrder createOrder = new CreateOrder(orderRepository, productRepository, priceCalculatorService)

    def 'should create a new order and return id'() {
        given:
            UUID productId = UUID.randomUUID()
            Integer productQuantity = 1
            BigDecimal productPrice = new BigDecimal(12.50)
            Product product = new Product(productId, "PROD", "product", productPrice)
            BigDecimal itemPrice = new BigDecimal(12.50)
        when:
            UUID result = createOrder.execute(productId, productQuantity)
        then:
            1 * productRepository.get(_ as UUID) >> product
            1 * priceCalculatorService.calculate(_ as Product, _ as Integer) >> itemPrice
            1 * orderRepository.save(_ as Order)
            result != null
    }

}
