package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.OrderFixture
import com.ecommerce.api.order.domain.model.BusinessException
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.Product
import com.ecommerce.api.order.domain.model.discount.PriceCalculatorService
import com.ecommerce.api.order.domain.port.OrderRepository
import com.ecommerce.api.order.domain.port.ProductRepository

import spock.lang.Specification
import spock.lang.Subject

class AddProductTest extends Specification {

    OrderRepository orderRepository = Mock()

    ProductRepository productRepository = Mock()

    PriceCalculatorService priceCalculatorService = Mock()

    @Subject
    AddProduct addProduct = new AddProduct(orderRepository, productRepository, priceCalculatorService)

    def 'should get an order and update it with a product'() {
        given:
            Order order = Spy(OrderFixture.anyOrder())
            UUID orderId = order.id().value()
            UUID productId = UUID.randomUUID()
            Integer productQuantity = 1
            BigDecimal productPrice = new BigDecimal(12.50)
            Product newProduct = new Product(productId, "BOOK", "newProduct", productPrice)
            BigDecimal itemPrice = new BigDecimal(12.50)
        when:
            addProduct.execute(orderId, productId, productQuantity)
        then:
            1 * orderRepository.findById(_ as UUID) >> Optional.of(order)
            1 * productRepository.findById(_ as UUID) >> Optional.of(newProduct)
            1 * priceCalculatorService.calculate(_ as Product, _ as Integer) >> itemPrice
            1 * order.addProduct(_ as Product, _ as Integer, _ as BigDecimal)
            1 * orderRepository.update(_ as Order)
    }

    def 'should throw business exception when do not find a order to update it with a product'() {
        given:
            Order order = OrderFixture.anyOrder()
            UUID orderId = order.id().value()
            UUID productId = UUID.randomUUID()
            Integer productQuantity = 1
        when:
            addProduct.execute(orderId, productId, productQuantity)
        then:
            1 * orderRepository.findById(_ as UUID) >> Optional.ofNullable(null)
            thrown(BusinessException)

    }

    def 'should throw business exception when do not find a product'() {
        given:
            Order order = OrderFixture.anyOrder()
            UUID orderId = order.id().value()
            UUID productId = UUID.randomUUID()
            Integer productQuantity = 1
        when:
            addProduct.execute(orderId, productId, productQuantity)
        then:
            1 * orderRepository.findById(_ as UUID) >> Optional.of(order)
            1 * productRepository.findById(_ as UUID) >> Optional.ofNullable(null)
            thrown(BusinessException)

    }

}
