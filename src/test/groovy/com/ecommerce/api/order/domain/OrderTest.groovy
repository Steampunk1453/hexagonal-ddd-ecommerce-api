package com.ecommerce.api.order.domain

import org.javamoney.moneta.Money

import com.ecommerce.api.order.domain.model.DomainException
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.Product

import spock.lang.Specification

class OrderTest extends Specification {

    def 'should create an order with a product'() {
        given:
            UUID id = UUID.randomUUID()
            Integer productQuantity = 4
            Money value = Money.of(new BigDecimal(2.50), "EUR")
            Product product = new Product(id, "PROD", "product", value)
        when:
            Order result = new Order(id, product, productQuantity)
        then:
            result.id == id
            result.orderItems.size() == 1
            result.orderItems[0].product() == product
            result.totalPrice == 10.00
    }

    def 'should add a product to an order'() {
        given:
            Order order = OrderProvider.buildOrder()
            Integer productQuantity = 2
            Money newValue = Money.of(new BigDecimal(2.50), "EUR")
            Product newProduct = new Product(UUID.randomUUID(), "NPROD", "newProduct", newValue)
        when:
            order.addProduct(newProduct, productQuantity)
            Order result = order
        then:
            result.id == order.id
            result.orderItems[1].product() == newProduct
            result.orderItems.size() == 2
            result.totalPrice == 7.50
    }

    def 'should remove a product from an order'() {
        given:
            Order order = OrderProvider.buildOrder()
            UUID productId = order.orderItems.get(0).product().code()
        when:
            order.removeProduct(productId)
            Order result = order
        then:
            result.orderItems.size() == 0
            result.totalPrice == BigDecimal.ZERO
    }

    def 'should throw domain exception when add a null product to an order'() {
        given:
            Order order = OrderProvider.buildOrder()
            Integer productQuantity = 2
        when:
            order.addProduct(null, productQuantity)
        then:
            thrown(DomainException)
    }

}
