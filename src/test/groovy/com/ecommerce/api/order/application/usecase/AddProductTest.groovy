package com.ecommerce.api.order.application.usecase

import org.javamoney.moneta.Money

import com.ecommerce.api.order.domain.OrderProvider
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.PriceCalculatorService
import com.ecommerce.api.order.domain.model.Product
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
            Order order = Spy(OrderProvider.buildOrder())
            UUID orderId = order.id
            UUID productId = UUID.randomUUID()
            Integer productQuantity = 1
            BigDecimal productPrice = new BigDecimal(12.50)
            Product newProduct = new Product(productId, "BOOK","newProduct", productPrice)
            BigDecimal itemPrice = new BigDecimal(12.50)
        when:
            addProduct.execute(orderId, productId, productQuantity)
        then:
            1 * orderRepository.get(_ as UUID) >> order
            1 * productRepository.get(_ as UUID) >> newProduct
            1 * priceCalculatorService.calculate(_ as Product, _ as Integer) >> itemPrice
            1 * order.addProduct(_ as Product, _ as Integer, _ as BigDecimal)
            1 * orderRepository.update(_ as Order)
    }

}
